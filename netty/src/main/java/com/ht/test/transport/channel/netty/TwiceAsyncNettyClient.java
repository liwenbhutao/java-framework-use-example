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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hutao on 16/5/14.
 * 下午6:13
 */
@Slf4j
@Deprecated
public class TwiceAsyncNettyClient extends BaseNettyClient implements TwiceAsyncClient<Msg> {
    private final ClientChannelHandler handler = new ClientChannelHandler(new ClientRequestContext(this));

    public TwiceAsyncNettyClient(final NettyClientConfig nettyClientConfig) {
        super(nettyClientConfig);
        setChannelHandler(new MsgChannelInitializer(handler, new CommandCodec()));
    }

    private void initFutureAndListener(final Listener listener) {
        handler.init(listener, new TwoResponseFuture());
    }

    private void writeAndFlush(final Object msg) {
        try {
            getChannel().writeAndFlush(msg);
        } catch (final Exception e) {
            handler.init(null, null);
            throw e;
        }
    }

    @Override
    public TwoResponseFuture asyncRequestWithTwoResponse(final Msg msg) {
        final TwoResponseFuture future = new TwoResponseFuture();
        handler.init(null, future);
        writeAndFlush(msg);
        return future;
    }


    @Override
    public void asyncRequestWithTwoResponse(final Msg msg,
                                            final Listener<Msg> callbackHandler) {
        final TwoResponseFuture future = new TwoResponseFuture();
        handler.init(callbackHandler, future);
        writeAndFlush(msg);
    }

    private static class ClientChannelHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
        private final AtomicInteger count = new AtomicInteger(0);
        private final ClientRequestContext clientRequestContext;
        private volatile TwoResponseFuture responseFuture;
        private volatile Listener listener;

        public ClientChannelHandler(final ClientRequestContext clientRequestContext) {
            this.clientRequestContext = clientRequestContext;
        }

        private synchronized void init(final Listener listener, final TwoResponseFuture future) {
            Preconditions.checkState(!isPreRequestNotFinish(), "有请求正在处理中");
            this.responseFuture = null;
            this.listener = null;
            this.count.set(0);
            this.responseFuture = future;
            this.listener = listener;
        }

        private boolean isPreRequestNotFinish() {
            return responseFuture != null &&
                    (FutureUtil.isNotFinish(responseFuture.getFirstResponseFuture()) ||
                            FutureUtil.isNotFinish(responseFuture.getSecondResponseFuture()));
        }

        @Override
        public void channelRead(final ChannelHandlerContext ctx, final Object msg)
                throws Exception {
            if (msg != null) {
                final int responseNum = count.addAndGet(1);
                if (responseNum == 1) {
                    if (responseFuture != null) {
                        getFirstResponseFuture().set(msg);
                    }
                    if (listener != null) {
                        listener.onFirstResponse(msg, clientRequestContext);
                    }
                } else if (responseNum == 2) {
                    if (responseFuture != null) {
                        getSecondResponseFuture().set(msg);
                    }
                    if (listener != null) {
                        listener.onSecondResponse(msg, clientRequestContext);
                    }
                }
            }
        }

        protected SettableFuture<Object> getSecondResponseFuture() {
            return (SettableFuture<Object>) responseFuture.getSecondResponseFuture();
        }

        protected SettableFuture<Object> getFirstResponseFuture() {
            return (SettableFuture<Object>) responseFuture.getFirstResponseFuture();
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
                throws Exception {
            notifyException(cause);
            init(null, null);
            ctx.close();
        }

        protected void notifyException(final Throwable cause) {
            if (responseFuture != null) {
                final SettableFuture<Object> firstResponseFuture = getFirstResponseFuture();
                if (FutureUtil.isNotFinish(firstResponseFuture)) {
                    firstResponseFuture.setException(cause);
                }
                final SettableFuture<Object> secondResponseFuture = getSecondResponseFuture();
                if (FutureUtil.isNotFinish(secondResponseFuture)) {
                    secondResponseFuture.setException(cause);
                }
            }
            if (listener != null) {
                listener.onException(cause, clientRequestContext);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            final RuntimeException exception = new RuntimeException("channel is close");
            notifyException(exception);
            init(null, null);
            super.channelInactive(ctx);
        }
    }
}
