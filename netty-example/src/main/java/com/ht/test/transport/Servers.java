package com.ht.test.transport;

import com.ht.test.transport.channel.Server;
import com.ht.test.transport.channel.netty.BaseNettyServer;
import com.ht.test.transport.channel.netty.CommandNettyServer;
import com.ht.test.transport.channel.netty.registry.ServerCommandHandlerRegistry;
import com.ht.test.transport.config.NettyServerConfig;
import io.netty.channel.ChannelHandler;
import lombok.experimental.UtilityClass;

/**
 * Created by hutao on 16/5/18.
 * 下午8:26
 */
@UtilityClass
public class Servers {
    public static Server createCommandNettyServer(final int port,
                                                  final ServerCommandHandlerRegistry registry) {
        return createCommandNettyServer(new NettyServerConfig().setPort(port), null, registry);
    }

    public static Server createCommandNettyServer(final NettyServerConfig nettyServerConfig,
                                                  final ServerCommandHandlerRegistry registry) {
        return createCommandNettyServer(nettyServerConfig, null, registry);
    }

    public static Server createCommandNettyServer(final NettyServerConfig nettyServerConfig,
                                                  final CommandNettyServer.CommandServerConfig commandServerConfig,
                                                  final ServerCommandHandlerRegistry registry) {
        return new CommandNettyServer(nettyServerConfig, commandServerConfig, registry);
    }

    public static Server createNettyServer(final int port,
                                           final ChannelHandler childChannelHandler) {
        return createNettyServer(new NettyServerConfig().setPort(port), childChannelHandler);
    }

    public static Server createNettyServer(final NettyServerConfig nettyServerConfig,
                                           final ChannelHandler childChannelHandler) {
        return new BaseNettyServer(nettyServerConfig, childChannelHandler);
    }
}
