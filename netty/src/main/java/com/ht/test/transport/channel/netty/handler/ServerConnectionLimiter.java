package com.ht.test.transport.channel.netty.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hutao on 16/5/14.
 * 上午11:15
 */
@RequiredArgsConstructor
@Slf4j
@ChannelHandler.Sharable
public class ServerConnectionLimiter extends ChannelInboundHandlerAdapter {
    private final AtomicInteger numConnections = new AtomicInteger(0);
    private final int maxConnections;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        if (maxConnections > 0 && numConnections.incrementAndGet() > maxConnections) {
            ctx.channel().close();
            log.info("Accepted connection above limit ({}). Dropping.", maxConnections);
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        if (maxConnections > 0 && numConnections.decrementAndGet() < 0) {
            log.error("BUG in ConnectionLimiter");
        }
        super.channelInactive(ctx);
    }
}
