package com.ht.test.transport.config;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import lombok.Getter;

import java.util.concurrent.ThreadFactory;

/**
 * Created by hutao on 16/5/14.
 * 上午11:11
 */
@Getter
public class NettyServerConfig extends ServerNetworkOptions<NettyServerConfig> {
    public final static NettyServerConfig DEFAULT = new NettyServerConfig();
    private ThreadFactory bossThreadFactory;
    private ThreadFactory workerThreadFactory;
    private ChannelHandler channelHandler;
    private ByteBufAllocator childByteBufAllocator;

    public NettyServerConfig setBossThreadFactory(final ThreadFactory bossThreadFactory) {
        this.bossThreadFactory = bossThreadFactory;
        return this;
    }

    public NettyServerConfig setWorkerThreadFactory(final ThreadFactory workerThreadFactory) {
        this.workerThreadFactory = workerThreadFactory;
        return this;
    }

    public NettyServerConfig setChannelHandler(final ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        return this;
    }

    public NettyServerConfig setChildByteBufAllocator(final ByteBufAllocator childByteBufAllocator) {
        this.childByteBufAllocator = childByteBufAllocator;
        return this;
    }
}
