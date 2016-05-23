package com.ht.test.netty.transport;

import com.beust.jcommander.internal.Maps;
import com.google.common.base.Throwables;
import com.ht.test.common.pool.BasePooledObjectFactory;
import com.ht.test.transport.Clients;
import com.ht.test.transport.Servers;
import com.ht.test.transport.channel.*;
import com.ht.test.transport.channel.netty.MsgTwiceAsyncNettyClient;
import com.ht.test.transport.channel.netty.RpcNettyClient;
import com.ht.test.transport.channel.netty.channel.MsgChannelInitializer;
import com.ht.test.transport.channel.netty.registry.NoLockServerCommandHandlerRegistry;
import com.ht.test.transport.channel.netty.supporter.ServerCommand;
import com.ht.test.transport.channel.netty.supporter.ServerCommandHandler;
import com.ht.test.transport.codec.CommandCodec;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.config.NettyServerConfig;
import com.ht.test.transport.enums.EnumBizType;
import com.ht.test.transport.protocol.Command;
import com.ht.test.transport.protocol.Msg;
import com.ht.test.transport.protocol.supporter.CommandSupporter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.testng.annotations.Test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hutao on 16/5/14.
 * 上午10:52
 */
@Slf4j
public class TransportTest {
    @Test
    public void startServer() throws Exception {
        final Map<EnumBizType, ServerCommandHandler> handlerMap = Maps.newHashMap();
        handlerMap.put(EnumBizType.UTS_A_SHARE, new ServerCommandHandler() {
            @Override
            protected void doHandle(final ServerCommand serverCommand) {
                log.debug("biz handler receive new command:{}", serverCommand.getCommand());
                serverCommand.getServerChannelContext().addResponseCommandToBus(serverCommand.getCommand());
                serverCommand.getServerChannelContext().addResponseCommandToBus(serverCommand.getCommand());
            }
        });

        try (final Server commandNettyServer = Servers.createCommandNettyServer(
                new NettyServerConfig()
                        .setPort(9999)
                        .setAcceptBacklog(2048)
                        .setTcpKeepAlive(true),
                new NoLockServerCommandHandlerRegistry(handlerMap))) {
            commandNettyServer.start();
        }
    }

    private void initPool() {
        final GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(40);
        genericObjectPoolConfig.setMaxTotal(80);
        genericObjectPoolConfig.setMinIdle(10);
        rpcNettyClientObjectPool = new GenericObjectPool<>(
                new BasePooledObjectFactory<>(() ->
                        new RpcNettyClient(new NettyClientConfig()
                                .setRemoteAddress(new InetSocketAddress("127.0.0.1", 9998)))),
                genericObjectPoolConfig);
    }

    private ObjectPool<RpcNettyClient> rpcNettyClientObjectPool;


    @Test
    public void startUtsServer() throws Exception {
        initPool();
        final Map<EnumBizType, ServerCommandHandler> handlerMap = Maps.newHashMap();
        handlerMap.put(EnumBizType.UTS_A_SHARE, new ServerCommandHandler() {
            @Override
            protected void doHandle(final ServerCommand serverCommand) {
                log.debug("biz handler receive new command:{}", serverCommand.getCommand());
                RpcNettyClient client = null;
                try {
                    client = rpcNettyClientObjectPool.borrowObject();
                    serverCommand.getServerChannelContext().addResponseCommandToBus(serverCommand.getCommand());
                    final Command response = (Command) client.request(serverCommand.getCommand());
                    serverCommand.getServerChannelContext().addResponseCommandToBus(response);
                } catch (Exception e) {
                    throw Throwables.propagate(e);
                } finally {
                    if (client != null) {
                        try {
                            rpcNettyClientObjectPool.returnObject(client);
                        } catch (Exception e) {
                            client.close();
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            }
        });

        try (final Server commandNettyServer = Servers.createCommandNettyServer(
                new NettyServerConfig()
                        .setPort(9999)
                        .setAcceptBacklog(2048)
                        .setTcpKeepAlive(true),
                new NoLockServerCommandHandlerRegistry(handlerMap))) {
            commandNettyServer.start();
        }

    }

    @Test
    public void startSimpleServer() throws Exception {
/*
        final ChannelInitializer<SocketChannel> childChannelHandler = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(final SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast("frameDecoder",
                        new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                socketChannel.pipeline().addLast("objectDecoder", new StringDecoder());

                socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
                socketChannel.pipeline().addLast("objectEncoder", new StringEncoder());

                socketChannel.pipeline().addLast(new ChannelHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ctx.channel().writeAndFlush(msg);
                        ctx.channel().writeAndFlush(msg);
                    }
                });
            }
        };
*/
        final Server server = Servers.createNettyServer(new NettyServerConfig()
                        .setPort(9999)
                        .setAcceptBacklog(2048)
                        .setReuseAddress(true)
                        .setTcpKeepAlive(true),
                new MsgChannelInitializer(new SimpleChannelHandler(), new CommandCodec()));
        server.start();
    }

    @Test
    public void startASharesAdapterServer() throws Exception {
        final Server server = Servers.createNettyServer(new NettyServerConfig()
                        .setPort(9998)
                        .setAcceptBacklog(2048)
                        .setReuseAddress(true)
                        .setTcpKeepAlive(true),
                new MsgChannelInitializer(new SimpleChannelHandler(), new CommandCodec()));
        server.start();

    }

    @ChannelHandler.Sharable
    private static class SimpleChannelHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.channel().writeAndFlush(msg);
            //ctx.channel().writeAndFlush(msg);
        }
    }

    @Test
    public void startMsgTwiceAsyncNettyClient() throws Exception {
        try (final TwiceAsyncClient client = new MsgTwiceAsyncNettyClient(new NettyClientConfig().setRemoteAddress(new InetSocketAddress("127.0.0.1", 9999)))) {
            client.connect();
            final List<TwiceAsyncClient.TwoResponseFuture> list = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                final String commandChannelId = CommandSupporter.generateCommandId();
                final TwiceAsyncClient.TwoResponseFuture future = client.asyncRequestWithTwoResponse(Command.of(commandChannelId + "dsafsdfaf", "dsafsff"));
                list.add(future);
            }
            for (final TwiceAsyncClient.TwoResponseFuture twoResponseFuture : list) {
                System.out.println(twoResponseFuture.getFirstResponseFuture().get());
                System.out.println(twoResponseFuture.getSecondResponseFuture().get());
            }
        }
    }

    @Test
    public void startTwiceAsyncClient() throws Exception {
        try (final TwiceAsyncClient twiceAsyncNettyClient = Clients.createTwiceAsyncNettyClient(new InetSocketAddress("127.0.0.1", 9999))) {
            twiceAsyncNettyClient.connect();
            final TwiceAsyncClient.TwoResponseFuture future = twiceAsyncNettyClient.asyncRequestWithTwoResponse(Command.of("dsafsdfaf", "dsafsff"));
            System.out.println(future.getFirstResponseFuture().get());
            System.out.println(future.getSecondResponseFuture().get());
        }
    }

    @Test
    public void startAsyncRpcNettyClient() throws Exception {
        try (final RpcClient<Msg> client = Clients.createRpcNettyClient(new InetSocketAddress("127.0.0.1", 9999))) {
            client.connect();
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            client.asyncRequest(buildCommand(), new RpcClient.Listener<Msg>() {
                @Override
                public void onResponse(Msg msg, ClientRequestContext<RpcClient> context) {
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable e, ClientRequestContext<RpcClient> context) {

                }
            });
            client.asyncRequest(buildCommand(), new RpcClient.Listener<Msg>() {
                @Override
                public void onResponse(Msg msg, ClientRequestContext<RpcClient> context) {
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable e, ClientRequestContext<RpcClient> context) {

                }
            });
            countDownLatch.await();
        }
    }

    @Test
    public void startSyncRpcNettyClient() throws Exception {
        try (final RpcClient<Msg> client = Clients.createRpcNettyClient(new InetSocketAddress("127.0.0.1", 9999))) {
            client.connect();
            for (int i = 0; i < 10; i++) {
                System.out.println(client.request(buildCommand()));
            }
        }
    }

    private Command buildCommand() {
        final String commandChannelId = CommandSupporter.generateCommandId();
        return Command.of(commandChannelId + "dsafsdfaf", "dsafsff");
    }

    @Test
    public void startMultipleAsyncClient() throws Exception {
        try (final MultipleAsyncClient client = Clients.createMultipleAsyncNettyClient(new InetSocketAddress("127.0.0.1", 9999))) {
            client.connect();
            final ExecutorService executorService = Executors.newFixedThreadPool(10);
            final CountDownLatch countDownLatch1 = new CountDownLatch(10);
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {
                    for (int i1 = 0; i1 < 10; i1++) {
                        final CountDownLatch countDownLatch = new CountDownLatch(2);
                        final String commandChannelId = CommandSupporter.generateCommandId();
                        final MultipleAsyncClient.MsgChannelId msgChannelId = MultipleAsyncClient.MsgChannelId.of(commandChannelId);
                        client.openMsgChannel(msgChannelId, new MultipleAsyncClient.Listener() {
                            @Override
                            public void onResponse(final Object msg,
                                                   final MultipleAsyncClient.ClientRequestContextImpl context) {
                                log.debug("msg channel receive msg:{}", msg);
                                countDownLatch.countDown();
                            }

                            @Override
                            public void onException(final Throwable e,
                                                    final MultipleAsyncClient.ClientRequestContextImpl context) {
                                log.error(e.getMessage(), e);
                                throw Throwables.propagate(e);
                            }
                        });
                        client.asyncRequestWithMultipleResponse(msgChannelId, Command.of(commandChannelId + "sdafafdf", "fadfsf"));
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            throw Throwables.propagate(e);
                        }
                        client.closeMsgChannel(msgChannelId);
                    }
                    countDownLatch1.countDown();
                });
            }
            countDownLatch1.await();


        }

    }

    @Test
    public void testName() throws Exception {
    }
}