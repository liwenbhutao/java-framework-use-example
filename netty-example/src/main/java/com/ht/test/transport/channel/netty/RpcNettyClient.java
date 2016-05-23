package com.ht.test.transport.channel.netty;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.SettableFuture;
import com.ht.test.transport.channel.ClientRequestContext;
import com.ht.test.transport.channel.RpcClient;
import com.ht.test.transport.channel.netty.channel.MsgChannelInitializer;
import com.ht.test.transport.codec.CommandCodec;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.protocol.Msg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/14.
 * 下午6:13
 */
@Slf4j
public class RpcNettyClient extends BaseNettyClient implements RpcClient<Msg> {
    private final ClientChannelHandler handler = new ClientChannelHandler(new ClientRequestContext(this));

    public RpcNettyClient(final NettyClientConfig nettyClientConfig) {
        super(nettyClientConfig);
        setChannelHandler(new MsgChannelInitializer(handler, new CommandCodec()));
    }

    @Override
    public Future<Msg> asyncRequest(final Msg msg) {
        final String msgIdStr = msg.getHeaderIdStr();
        final Pair<SettableFuture<Msg>, Listener> futureListenerPair = handler.addMsgFutureListenerPair(msgIdStr, null);
        writeAndFlush(msgIdStr, msg);
        return futureListenerPair.getLeft();
    }

    private void writeAndFlush(final String msgId, final Msg msg) {
        try {
            getChannel().writeAndFlush(msg);
        } catch (final Exception e) {
            handler.removeMsgFutureListenerPair(msgId);
            throw e;
        }
    }

    @Override
    public void asyncRequest(final Msg msg, final Listener<Msg> listener) {
        final String msgIdStr = msg.getHeaderIdStr();
        handler.addMsgFutureListenerPair(msgIdStr, listener);
        writeAndFlush(msgIdStr, msg);
    }

    @Override
    public Msg request(final Msg msg) {
        try {
            return asyncRequest(msg).get();
        } catch (final Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private static class ClientChannelHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
        private final ConcurrentMap<String, Pair<SettableFuture<Msg>, Listener>> futureListenerConcurrentMap = new ConcurrentHashMap<>();
        private final ClientRequestContext clientRequestContext;

        public ClientChannelHandler(final ClientRequestContext clientRequestContext) {
            this.clientRequestContext = clientRequestContext;
        }

        public Pair<SettableFuture<Msg>, Listener> addMsgFutureListenerPair(final String msgId, final Listener<Msg> listener) {
            final Pair<SettableFuture<Msg>, Listener> pair = Pair.of(SettableFuture.create(), listener);
            futureListenerConcurrentMap.put(msgId, pair);
            return pair;
        }

        public Pair<SettableFuture<Msg>, Listener> removeMsgFutureListenerPair(final String msgId) {
            return futureListenerConcurrentMap.remove(msgId);
        }

        @Override
        public void channelRead(final ChannelHandlerContext ctx, final Object msg)
                throws Exception {
            if (msg != null && msg instanceof Msg) {
                final Msg realMsg = (Msg) msg;
                final String msgId = realMsg.getHeader().getIdStr();
                final Pair<SettableFuture<Msg>, Listener> futureListenerPair = futureListenerConcurrentMap.remove(msgId);
                if (futureListenerPair != null) {
                    futureListenerPair.getLeft().set(realMsg);
                    if (futureListenerPair.getRight() != null) {
                        futureListenerPair.getRight().onResponse(realMsg, clientRequestContext);
                    }
                }
            }
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
                throws Exception {
            notifyExceptions(cause);
            futureListenerConcurrentMap.clear();
            ctx.close();
        }

        void notifyException(final Throwable e, final Pair<SettableFuture<Msg>, Listener> futureListenerPair) {
            futureListenerPair.getLeft().setException(e);
            if (futureListenerPair.getRight() != null) {
                futureListenerPair.getRight().onException(e, clientRequestContext);
            }
        }

        void notifyExceptions(final Throwable e) {
            for (final Pair<SettableFuture<Msg>, Listener> futureListenerPair : futureListenerConcurrentMap.values()) {
                notifyException(e, futureListenerPair);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            notifyExceptions(new RuntimeException("channel is close"));
            futureListenerConcurrentMap.clear();
            super.channelInactive(ctx);
        }
    }
}
