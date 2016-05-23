package com.ht.test.transport.channel.netty;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.SettableFuture;
import com.ht.test.common.util.FutureUtil;
import com.ht.test.transport.channel.ClientRequestContext;
import com.ht.test.transport.channel.TwiceAsyncClient;
import com.ht.test.transport.channel.netty.channel.MsgChannelInitializer;
import com.ht.test.transport.codec.CommandCodec;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.protocol.Msg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hutao on 16/5/14.
 * 下午6:13
 */
@Slf4j
public class MsgTwiceAsyncNettyClient extends BaseNettyClient implements TwiceAsyncClient<Msg> {
    private final ClientChannelHandler handler;

    public MsgTwiceAsyncNettyClient(final NettyClientConfig nettyClientConfig) {
        super(nettyClientConfig);
        this.handler = new ClientChannelHandler(new ClientRequestContext(this));
        setChannelHandler(new MsgChannelInitializer(this.handler, new CommandCodec()));
    }

    private void writeAndFlush(final Msg msg,
                               final String msgId) {
        try {
            getChannel().writeAndFlush(msg);
        } catch (final Exception e) {
            handler.removeMsgResponseFuture(msgId);
            throw e;
        }
    }

    @Override
    public TwoResponseFuture asyncRequestWithTwoResponse(final Msg msg) {
        final String msgId = msg.getHeader().getIdStr();
        final Triple<TwoResponseFuture, Listener, AtomicInteger> triple = handler.addMsgResponseFuture(msgId, null);
        writeAndFlush(msg, msgId);
        return triple.getLeft();
    }


    @Override
    public void asyncRequestWithTwoResponse(final Msg msg,
                                            final Listener<Msg> callbackHandler) {
        final String msgId = msg.getHeader().getIdStr();
        handler.addMsgResponseFuture(msgId, callbackHandler);
        writeAndFlush(msg, msgId);
    }

    private static class ClientChannelHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
        private final ConcurrentMap<String, Triple<TwoResponseFuture, Listener, AtomicInteger>> twoResponseFutureConcurrentMap = new ConcurrentHashMap<>();
        private final ClientRequestContext clientRequestContext;
        private volatile boolean isClose = true;

        public ClientChannelHandler(final ClientRequestContext clientRequestContext) {
            this.clientRequestContext = clientRequestContext;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            isClose = false;
        }

        public Triple<TwoResponseFuture, Listener, AtomicInteger> addMsgResponseFuture(final String msgId,
                                                                                       final Listener listener) {
            Preconditions.checkState(!isClose, "client 状态异常");
            Preconditions.checkState(!twoResponseFutureConcurrentMap.containsKey(msgId), "有正在处理中的相同的msgId");
            final TwoResponseFuture twoResponseFuture = new TwoResponseFuture();
            final Triple<TwoResponseFuture, Listener, AtomicInteger> triple = Triple.of(twoResponseFuture, listener, new AtomicInteger(0));
            twoResponseFutureConcurrentMap.put(msgId, triple);
            return triple;
        }

        public Triple<TwoResponseFuture, Listener, AtomicInteger> removeMsgResponseFuture(final String msgId) {
            return twoResponseFutureConcurrentMap.remove(msgId);
        }

        @Override
        public void channelRead(final ChannelHandlerContext ctx, final Object msg)
                throws Exception {
            if (msg != null && msg instanceof Msg) {
                final Msg realMsg = (Msg) msg;
                final String msgId = realMsg.getHeader().getIdStr();
                final Triple<TwoResponseFuture, Listener, AtomicInteger> triple = twoResponseFutureConcurrentMap.get(msgId);
                if (triple != null) {
                    final int responseNum = triple.getRight().addAndGet(1);
                    if (responseNum == 1) {
                        getFirstResponseFuture(triple).set(msg);
                        final Listener listener = triple.getMiddle();
                        if (listener != null) {
                            listener.onFirstResponse(msg, clientRequestContext);
                        }
                    } else if (responseNum == 2) {
                        getSecondResponseFuture(triple).set(msg);
                        final Listener listener = triple.getMiddle();
                        if (listener != null) {
                            listener.onSecondResponse(msg, clientRequestContext);
                        }
                        removeMsgResponseFuture(msgId);
                    }
                }

            }
        }

        private SettableFuture<Object> getSecondResponseFuture(final Triple<TwoResponseFuture, Listener, AtomicInteger> triple) {
            return (SettableFuture<Object>) triple.getLeft().getSecondResponseFuture();
        }

        private SettableFuture<Object> getFirstResponseFuture(final Triple<TwoResponseFuture, Listener, AtomicInteger> triple) {
            return (SettableFuture<Object>) triple.getLeft().getFirstResponseFuture();
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
                throws Exception {
            isClose = true;
            notifyException(cause);
            twoResponseFutureConcurrentMap.clear();
            ctx.close();
        }

        private void notifyException(final Throwable cause,
                                     final Triple<TwoResponseFuture, Listener, AtomicInteger> triple) {
            try {
                final SettableFuture<Object> firstResponseFuture = getFirstResponseFuture(triple);
                if (FutureUtil.isNotFinish(firstResponseFuture)) {
                    firstResponseFuture.setException(cause);
                }
                final SettableFuture<Object> secondResponseFuture = getSecondResponseFuture(triple);
                if (FutureUtil.isNotFinish(secondResponseFuture)) {
                    secondResponseFuture.setException(cause);
                }
                final Listener listener = triple.getMiddle();
                if (listener != null) {
                    listener.onException(cause, clientRequestContext);
                }
            } catch (final Exception e) {
                log.error("notify client response exception error:{]", e.getMessage(), e);
            }
        }

        private void notifyException(final Throwable cause) {
            for (final Triple<TwoResponseFuture, Listener, AtomicInteger> triple : twoResponseFutureConcurrentMap.values()) {
                notifyException(cause, triple);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            notifyException(new RuntimeException("channel is close"));
            twoResponseFutureConcurrentMap.clear();
            super.channelInactive(ctx);
        }
    }
}
