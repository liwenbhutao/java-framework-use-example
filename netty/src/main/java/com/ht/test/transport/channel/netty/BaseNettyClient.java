package com.ht.test.transport.channel.netty;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.ht.test.common.pool.PooledObject;
import com.ht.test.transport.channel.AbstractClient;
import com.ht.test.transport.config.NettyClientConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by hutao on 16/5/12.
 * 下午2:24
 */
@Slf4j
public class BaseNettyClient extends AbstractClient implements PooledObject {
    private final NettyClientConfig nettyClientConfig;
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private ChannelHandler channelHandler;
    private volatile boolean closed = true;
    private Bootstrap bootstrap;
    private Channel channel;
    @Getter(AccessLevel.PROTECTED)
    private String channelId = RandomStringUtils.randomNumeric(10) + System.currentTimeMillis();

    protected BaseNettyClient(final NettyClientConfig nettyClientConfig) {
        this(nettyClientConfig, null);
    }

    public BaseNettyClient(@NonNull final NettyClientConfig nettyClientConfig,
                           final ChannelHandler channelHandler) {
        super(nettyClientConfig);
        this.nettyClientConfig = nettyClientConfig;
        this.channelHandler = channelHandler;
    }

    protected final void applyConnectionOptions(final Bootstrap bootstrap) {
        bootstrap.option(ChannelOption.TCP_NODELAY, this.getOptions().isTcpNoDelay());
        if (this.getOptions().getSendBufferSize() != -1) {
            bootstrap.option(ChannelOption.SO_SNDBUF, this.getOptions().getSendBufferSize());
        }

        if (this.getOptions().getReceiveBufferSize() != -1) {
            bootstrap.option(ChannelOption.SO_RCVBUF, this.getOptions().getReceiveBufferSize());
            bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,
                    new FixedRecvByteBufAllocator(this.getOptions().getReceiveBufferSize()));
        }

        if (this.getOptions().getSoLinger() != -1) {
            bootstrap.option(ChannelOption.SO_LINGER, this.getOptions().getSoLinger());
        }

        if (this.getOptions().getTrafficClass() != -1) {
            bootstrap.option(ChannelOption.IP_TOS, this.getOptions().getTrafficClass());
        }

        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
                this.getOptions().getConnectTimeout());
        bootstrap.option(ChannelOption.ALLOCATOR,
                this.nettyClientConfig.getChildByteBufAllocator() != null ?
                        this.nettyClientConfig.getChildByteBufAllocator() :
                        PooledByteBufAllocator.DEFAULT);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, this.getOptions().isTcpKeepAlive());
    }

    private void checkClosed() {
        if (!this.closed) {
            throw new IllegalStateException("Client is closed");
        }
    }

    @Override
    public void connect() {
        checkClosed();
        Preconditions.checkNotNull(getChannelHandler(),
                "client handler can not be null");
        Preconditions.checkNotNull(this.nettyClientConfig.getRemoteAddress(),
                "client remote address can not be null");
        this.bootstrap = getBootstrap();
        applyConnectionOptions(bootstrap);
        try {
            final ChannelFuture channelFuture = this.bootstrap.connect(
                    this.nettyClientConfig.getRemoteAddress());
            channelFuture.sync();
            this.channel = channelFuture.channel();
            this.closed = false;
        } catch (InterruptedException e) {
            throw Throwables.propagate(e);
        }
    }

    protected Bootstrap getBootstrap() {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(this.nettyClientConfig.getThreadFactory() == null ?
                new NioEventLoopGroup() :
                new NioEventLoopGroup(0, this.nettyClientConfig.getThreadFactory()));
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(getChannelHandler());
        if (this.nettyClientConfig.getChannelFactory() != null) {
            bootstrap.channelFactory(this.nettyClientConfig.getChannelFactory());
        }
        return bootstrap;
    }

    protected ChannelPipeline buildChannelPipeline(final ChannelPipeline pipeline) {
        return pipeline;
    }

    protected EventLoopGroup getNettyWorkEventLoop() {
        return new NioEventLoopGroup();
    }

    protected final Channel getChannel() {
        return this.channel;
    }

    @Override
    public boolean isConnected() {
        return this.channel != null && this.channel.isActive();
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        if (this.channel != null) {
            try {
                this.channel.close();
            } catch (final Exception e) {
                log.error("close netty client error:{}", e.getMessage(), e);
            }
            this.channel = null;
        }

        if (this.bootstrap != null) {
            try {
                this.bootstrap.group().shutdownGracefully();
            } catch (Exception e) {
                log.error("close netty client error:{}", e.getMessage(), e);
            }
            this.bootstrap = null;
        }
        this.closed = true;
        this.bootstrap = null;
        this.channel = null;
    }

    @Override
    public void active() {
        connect();
    }

    @Override
    public boolean isActive() {
        return isConnected();
    }
}
