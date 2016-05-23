package com.ht.test.transport.channel.netty;

import com.google.common.base.Optional;
import com.ht.test.transport.channel.netty.channel.MsgChannelInitializer;
import com.ht.test.transport.channel.netty.registry.ServerCommandHandlerRegistry;
import com.ht.test.transport.channel.netty.supporter.ServerChannelContext;
import com.ht.test.transport.channel.netty.supporter.ServerCommand;
import com.ht.test.transport.channel.netty.supporter.ServerCommandHandler;
import com.ht.test.transport.codec.CommandCodec;
import com.ht.test.transport.config.NettyServerConfig;
import com.ht.test.transport.constant.Constants;
import com.ht.test.transport.enums.EnumBizType;
import com.ht.test.transport.protocol.Command;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.registry.Registration;
import reactor.core.dispatch.RingBufferDispatcher;
import reactor.fn.Consumer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by hutao on 16/5/18.
 * 下午7:46
 */
@Slf4j
public class CommandNettyServer extends BaseNettyServer {
    private final EventBus outBus;
    private final EventBus inBus;

    public CommandNettyServer(final NettyServerConfig nettyServerConfig,
                              final CommandServerConfig commandServerConfig,
                              final ServerCommandHandlerRegistry registry) {
        super(nettyServerConfig);
        final int inBusBufferSize = commandServerConfig == null ?
                CommandServerConfig.DEFAULT_IN_BUS_RING_BUFFER_SIZE :
                commandServerConfig.getInBusRingBufferSize();
        final int outBusBufferSize = commandServerConfig == null ?
                CommandServerConfig.DEFAULT_OUT_BUS_RING_BUFFER_SIZE :
                commandServerConfig.getOutBusRingBufferSize();
        outBus = EventBus.create(new Environment(), new RingBufferDispatcher("outBus", inBusBufferSize));
        inBus = EventBus.create(new Environment(), new RingBufferDispatcher("inBus", outBusBufferSize));
        for (final EnumBizType bizType : EnumBizType.values()) {
            final Optional<ServerCommandHandler> serverCommandHandlerOptional = registry.lookUp(bizType);
            if (serverCommandHandlerOptional.isPresent()) {
                register(bizType, serverCommandHandlerOptional.get());
            }
        }
        setChildChannelHandler(new MsgChannelInitializer(new ServerChannelHandler(inBus, outBus), new CommandCodec()));
    }

    public CommandNettyServer(final NettyServerConfig nettyServerConfig,
                              final ServerCommandHandlerRegistry registry) {
        this(nettyServerConfig, null, registry);
    }

    private static String getChannelId(final Channel channel) {
        return channel.remoteAddress().toString();
    }

    private void register(final EnumBizType bizType, final ServerCommandHandler consumer) {
        log.debug("add biz[{}] consumer", bizType);
        inBus.on($(bizType.getTopic()), consumer);
    }

    public static class CommandServerConfig {
        public static final int DEFAULT_IN_BUS_RING_BUFFER_SIZE = 2048;
        public static final int DEFAULT_OUT_BUS_RING_BUFFER_SIZE = 2048;
        private int inBusRingBufferSize = DEFAULT_IN_BUS_RING_BUFFER_SIZE;
        private int outBusRingBufferSize = DEFAULT_OUT_BUS_RING_BUFFER_SIZE;

        public int getInBusRingBufferSize() {
            return inBusRingBufferSize;
        }

        public CommandServerConfig setInBusRingBufferSize(final int inBusRingBufferSize) {
            this.inBusRingBufferSize = inBusRingBufferSize;
            return this;
        }

        public int getOutBusRingBufferSize() {
            return outBusRingBufferSize;
        }

        public CommandServerConfig setOutBusRingBufferSize(final int outBusRingBufferSize) {
            this.outBusRingBufferSize = outBusRingBufferSize;
            return this;
        }
    }

    @ChannelHandler.Sharable
    private static class ServerChannelHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
        private final EventBus inBus;
        private final EventBus outBus;
        private final ConcurrentMap<String, ServerChannelContext> serverChannelContextMap = new ConcurrentHashMap<>();

        public ServerChannelHandler(final EventBus inBus, final EventBus outBus) {
            this.inBus = inBus;
            this.outBus = outBus;
        }

        @Override
        public void channelRead(final ChannelHandlerContext ctx, final Object msg)
                throws Exception {
            if (msg != null && msg instanceof Command) {
                final Command command = (Command) msg;
                log.debug("receive new command:{}", msg);
                final String channelId = getChannelId(ctx.channel());
                inBus.notify(EnumBizType.UTS_A_SHARE.getTopic(), Event.wrap(
                        new ServerCommand(command, serverChannelContextMap.get(channelId))));
            }
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
                throws Exception {
            super.exceptionCaught(ctx, cause);
            cancelChannelConsumerRegistration(getChannelId(ctx.channel()));
            ctx.close();
        }

        @Override
        public void channelActive(final ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            final String channelId = getChannelId(ctx.channel());
            final String channelOutTopic = Constants.SERVER_CHANNEL_OUT_TOPIC_PREFIX + channelId;
            log.debug("add channel[id={}] out bus consumer on topic[{}]", channelId, channelOutTopic);
            cancelChannelConsumerRegistration(channelId);
            final Registration<Object, Consumer<? extends Event<?>>> consumerRegistration = outBus
                    .on($(channelOutTopic),
                            new Consumer<Event<Command>>() {
                                @Override
                                public void accept(final Event<Command> event) {
                                    log.info("write a command to client,channelId[{}],content:{}", channelId, event.getData());
                                    ctx.channel().writeAndFlush(event.getData());
                                }
                            });

            serverChannelContextMap.put(channelId, ServerChannelContext.builder()
                    .channelId(channelId)
                    .channelOutTopic(channelOutTopic)
                    .consumerRegistration(consumerRegistration)
                    .outBus(outBus)
                    .build());
        }

        private void cancelChannelConsumerRegistration(final String channelId) {
            if (serverChannelContextMap.containsKey(channelId)) {
                final Registration<Object, Consumer<? extends Event<?>>> consumerRegistration = serverChannelContextMap
                        .get(channelId).getConsumerRegistration();
                consumerRegistration.cancel();
                serverChannelContextMap.remove(channelId);
            }
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            cancelChannelConsumerRegistration(getChannelId(ctx.channel()));
            super.channelInactive(ctx);
        }
    }

}
