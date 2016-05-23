package com.ht.test.transport.config;

import io.netty.bootstrap.ChannelFactory;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import lombok.Getter;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;

/**
 * Created by hutao on 16/5/14.
 * 上午11:11
 */
@Getter
public class NettyClientConfig extends ClientNetworkOptions<NettyClientConfig> {
    private ThreadFactory threadFactory;
    private ChannelFactory<Channel> channelFactory;
    private ByteBufAllocator childByteBufAllocator;
    private InetSocketAddress remoteAddress;

    public NettyClientConfig setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public NettyClientConfig setChannelFactory(ChannelFactory<Channel> channelFactory) {
        this.channelFactory = channelFactory;
        return this;
    }

    public NettyClientConfig setChildByteBufAllocator(ByteBufAllocator childByteBufAllocator) {
        this.childByteBufAllocator = childByteBufAllocator;
        return this;
    }

    public NettyClientConfig setRemoteAddress(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }
}
