package com.ht.test.transport.channel.netty;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.ht.test.transport.channel.AbstractServer;
import com.ht.test.transport.config.NettyServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/13.
 * 下午6:42
 */
@Slf4j
public class BaseNettyServer extends AbstractServer {
    private final NettyServerConfig nettyServerConfig;
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PRIVATE)
    private ChannelHandler childChannelHandler;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private Channel channel;
    private volatile ServerState state = ServerState.CLOSE;

    public BaseNettyServer(final NettyServerConfig nettyServerConfig,
                           final ChannelHandler childChannelHandler) {
        super(nettyServerConfig);
        this.nettyServerConfig = nettyServerConfig == null ? NettyServerConfig.DEFAULT : nettyServerConfig;
        this.childChannelHandler = childChannelHandler;
    }

    protected BaseNettyServer(final NettyServerConfig nettyServerConfig) {
        this(nettyServerConfig, null);
    }

    @Override
    public void start() {
        Preconditions.checkNotNull(getChildChannelHandler(),
                "netty server child handler can not be null");
        Preconditions.checkState(this.getOptions().getPort() > 0,
                "netty server port is wrong");
        final ServerBootstrap bootstrap = getServerBootstrap();
        run(this.getOptions().getPort(), bootstrap);
    }

    protected void run(final int port, final ServerBootstrap bootstrap) {
        try {
            final ChannelFuture channelFuture = bootstrap.bind(port).sync();
            this.state = ServerState.ACTIVE;
            this.channel = channelFuture.channel();
            log.info("netty server begin listen on port:{}" + port);
            channelFuture.channel().closeFuture().sync();
        } catch (final InterruptedException e) {
            throw Throwables.propagate(e);
        }
    }

    protected ServerBootstrap getServerBootstrap() {
        final ServerBootstrap bootstrap = new ServerBootstrap();
        bossGroup = nettyServerConfig.getBossThreadFactory() != null ?
                new NioEventLoopGroup(0, nettyServerConfig.getBossThreadFactory()) :
                new NioEventLoopGroup();
        workGroup = nettyServerConfig.getWorkerThreadFactory() != null ?
                new NioEventLoopGroup(0, nettyServerConfig.getWorkerThreadFactory()) :
                new NioEventLoopGroup();
        bootstrap.group(bossGroup, workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(getChildChannelHandler());
        bootstrap.handler(nettyServerConfig.getChannelHandler() != null ?
                nettyServerConfig.getChannelHandler() : new LoggingHandler(LogLevel.INFO));
        bootstrap.childOption(ChannelOption.ALLOCATOR,
                nettyServerConfig.getChildByteBufAllocator() == null ?
                        PooledByteBufAllocator.DEFAULT :
                        nettyServerConfig.getChildByteBufAllocator());
        applyConnectionOptions(bootstrap);
        return bootstrap;
    }

    protected final void applyConnectionOptions(final ServerBootstrap bootstrap) {
        bootstrap.childOption(ChannelOption.TCP_NODELAY, getOptions().isTcpNoDelay());
        if (getOptions().getSendBufferSize() != -1) {
            bootstrap.childOption(ChannelOption.SO_SNDBUF, getOptions().getSendBufferSize());
        }
        if (getOptions().getReceiveBufferSize() != -1) {
            bootstrap.childOption(ChannelOption.SO_RCVBUF, getOptions().getReceiveBufferSize());
            bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR,
                    new FixedRecvByteBufAllocator(getOptions().getReceiveBufferSize()));
        }
        if (getOptions().getSoLinger() != -1) {
            bootstrap.option(ChannelOption.SO_LINGER, getOptions().getSoLinger());
        }
        if (getOptions().getTrafficClass() != -1) {
            bootstrap.childOption(ChannelOption.IP_TOS, getOptions().getTrafficClass());
        }
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, getOptions().isTcpKeepAlive());
        bootstrap.option(ChannelOption.SO_REUSEADDR, getOptions().isReuseAddress());
        if (getOptions().getAcceptBacklog() != -1) {
            bootstrap.option(ChannelOption.SO_BACKLOG, getOptions().getAcceptBacklog());
        }
    }

    @Override
    public boolean isActive() {
        return state == ServerState.ACTIVE;
    }

    @Override
    public boolean isClosed() {
        return state == ServerState.CLOSE;
    }


    @Override
    public void close() throws Exception {
        if (isActive()) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (Exception e) {
                    log.error("close server channel error:{}", e.getMessage(), e);
                }
                channel = null;
            }

            if (bossGroup != null) {
                try {
                    bossGroup.shutdownGracefully();
                } catch (Exception e) {
                    log.error("close server boss group error:{}", e.getMessage(), e);
                }
                bossGroup = null;
            }

            if (workGroup != null) {
                try {
                    workGroup.shutdownGracefully();
                } catch (Exception e) {
                    log.error("close server work group error:{}", e.getMessage(), e);
                }
                workGroup = null;
            }

            state = ServerState.CLOSE;
        }
    }
}
