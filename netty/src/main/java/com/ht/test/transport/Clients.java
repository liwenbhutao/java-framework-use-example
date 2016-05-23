package com.ht.test.transport;

import com.ht.test.transport.channel.MultipleAsyncClient;
import com.ht.test.transport.channel.RpcClient;
import com.ht.test.transport.channel.TwiceAsyncClient;
import com.ht.test.transport.channel.netty.MsgTwiceAsyncNettyClient;
import com.ht.test.transport.channel.netty.MultipleAsyncNettyClient;
import com.ht.test.transport.channel.netty.RpcNettyClient;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.protocol.Msg;
import lombok.experimental.UtilityClass;
import reactor.Environment;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hutao on 16/5/18.
 * 下午7:08
 */
@UtilityClass
public class Clients {
    public static void initialize() {

    }

    public static void shutdown() {

    }

    public static RpcClient<Msg> createRpcNettyClient(final NettyClientConfig nettyClientConfig) {
        return new RpcNettyClient(nettyClientConfig);
    }

    public static RpcClient<Msg> createRpcNettyClient(final InetSocketAddress remoteAddress) {
        return new RpcNettyClient(new NettyClientConfig().setRemoteAddress(remoteAddress));
    }

    public static TwiceAsyncClient createTwiceAsyncNettyClient(final NettyClientConfig nettyClientConfig) {
        return new MsgTwiceAsyncNettyClient(nettyClientConfig);
    }

    public static TwiceAsyncClient createTwiceAsyncNettyClient(final InetSocketAddress remoteAddress) {
        return new MsgTwiceAsyncNettyClient(new NettyClientConfig().setRemoteAddress(remoteAddress));
    }

    public static MultipleAsyncClient createMultipleAsyncNettyClient(final InetSocketAddress remoteAddress) {

        final NettyClientConfig nettyClientConfig = new NettyClientConfig().setRemoteAddress(remoteAddress);
        return createMultipleAsyncNettyClient(nettyClientConfig, ClientsImpl.DEFAULT_BUS);
    }

    public static MultipleAsyncClient createMultipleAsyncNettyClient(final NettyClientConfig nettyClientConfig) {

        return createMultipleAsyncNettyClient(nettyClientConfig, ClientsImpl.DEFAULT_BUS);
    }

    public static MultipleAsyncClient createMultipleAsyncNettyClient(final NettyClientConfig nettyClientConfig,
                                                                     final EventBus eventBus) {

        return new MultipleAsyncNettyClient(nettyClientConfig, eventBus);
    }

    private static class ClientsImpl {
        private final static Executor DEFAULT_EXECUTOR = Executors.newCachedThreadPool();

        private final static EventBus DEFAULT_BUS = EventBus.create(new Environment(), new RingBufferDispatcher("inBus", 2048));
    }


}
