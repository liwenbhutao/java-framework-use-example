package com.ht.test.transport.channel.netty;

import com.ht.test.transport.channel.MultipleAsyncClient;
import com.ht.test.transport.channel.netty.channel.MsgChannelInitializer;
import com.ht.test.transport.channel.netty.supporter.CommandConsumer;
import com.ht.test.transport.channel.netty.supporter.ExceptionListener;
import com.ht.test.transport.codec.CommandCodec;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.constant.Constants;
import com.ht.test.transport.protocol.Command;
import com.ht.test.transport.protocol.Msg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.registry.Registration;
import reactor.fn.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by hutao on 16/5/14.
 * 下午6:13
 */
@Slf4j
public class MultipleAsyncNettyClient extends BaseNettyClient implements MultipleAsyncClient<Msg> {
    private final EventBus bus;
    private final ClientChannelHandler handler;
    private final ConcurrentMap<MsgChannelId, List<Registration<Object, Consumer<? extends Event<?>>>>> msgChannelIdRegistrationsConcurrentHashMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<MsgChannelId, ClientRequestContextImpl> idClientRequestContextConcurrentHashMap = new ConcurrentHashMap<>();

    public MultipleAsyncNettyClient(final NettyClientConfig nettyClientConfig,
                                    final EventBus bus) {
        super(nettyClientConfig);
        this.bus = bus;
        this.handler = new ClientChannelHandler(bus, getChannelId());
        setChannelHandler(new MsgChannelInitializer(this.handler, new CommandCodec()));
    }

    private static String getExceptionTopic(final String channelId) {
        return Constants.CLIENT_EXCEPTION_TOPIC_PREFIX + channelId;
    }

    private static String getMsgChannelTopic(final String msgChannelIdId) {
        return Constants.CLIENT_COMMAND_TOPIC_PREFIX + msgChannelIdId;
    }

    private List<Registration<Object, Consumer<? extends Event<?>>>> getMsgChannelRegistrationList(final MsgChannelId msgChannelId) {
        if (!msgChannelIdRegistrationsConcurrentHashMap.containsKey(msgChannelId)) {
            msgChannelIdRegistrationsConcurrentHashMap.put(msgChannelId, new ArrayList<>());
        }
        return msgChannelIdRegistrationsConcurrentHashMap.get(msgChannelId);
    }

    @Override
    public void openMsgChannel(final MsgChannelId msgChannelId,
                               final Listener listener) {
        idClientRequestContextConcurrentHashMap.put(msgChannelId, new ClientRequestContextImpl(this, msgChannelId));
        final List<Registration<Object, Consumer<? extends Event<?>>>> msgChannelRegistrationList = getMsgChannelRegistrationList(msgChannelId);
        final Registration<Object, Consumer<? extends Event<?>>> msgConsumerRegistration = bus.on(
                $(getMsgChannelTopic(msgChannelId.getId())),
                new CommandConsumer() {
                    @Override
                    protected void doHandle(final Command command) {
                        listener.onResponse(command, idClientRequestContextConcurrentHashMap.get(msgChannelId));
                    }
                });
        msgChannelRegistrationList.add(msgConsumerRegistration);
        final Registration<Object, Consumer<? extends Event<?>>> exceptionConsumerRegistration = bus.on(
                $(getExceptionTopic(getChannelId())),
                new ExceptionListener() {
                    @Override
                    protected void doHandle(Throwable e) {
                        listener.onException(e, idClientRequestContextConcurrentHashMap.get(msgChannelId));
                    }
                });
        msgChannelRegistrationList.add(exceptionConsumerRegistration);
    }

    @Override
    public void close() {
        super.close();
        for (final List<Registration<Object, Consumer<? extends Event<?>>>> registrationList :
                msgChannelIdRegistrationsConcurrentHashMap.values()) {
            for (final Registration<Object, Consumer<? extends Event<?>>> registration : registrationList) {
                registration.cancel();
            }
        }
        msgChannelIdRegistrationsConcurrentHashMap.clear();

    }

    @Override
    public void closeMsgChannel(final MsgChannelId msgChannelId) {
        if (msgChannelIdRegistrationsConcurrentHashMap.containsKey(msgChannelId)) {
            final List<Registration<Object, Consumer<? extends Event<?>>>> registrationList =
                    msgChannelIdRegistrationsConcurrentHashMap.remove(msgChannelId);
            for (final Registration<Object, Consumer<? extends Event<?>>> registration : registrationList) {
                registration.cancel();
            }
        }
    }

    @Override
    public void asyncRequestWithMultipleResponse(final MsgChannelId msgChannelId,
                                                 final Msg msg) {
        getChannel().writeAndFlush(msg);
    }

    private static class ClientChannelHandler extends ChannelInboundHandlerAdapter implements ChannelHandler {
        private final EventBus bus;
        private final String channelId;

        public ClientChannelHandler(final EventBus bus, final String channelId) {
            this.bus = bus;
            this.channelId = channelId;
        }

        @Override
        public void channelRead(final ChannelHandlerContext ctx, final Object msg)
                throws Exception {
            if (msg != null && msg instanceof Command) {
                final Command command = (Command) msg;
                log.info("client receive command response:" + command.toString());
                final String commandId = command.getHeader().getIdStr();
                final String commandTopic = getMsgChannelTopic(commandId);
                bus.notify(commandTopic, Event.wrap(command));
            }
        }

        @Override
        public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
                throws Exception {
            log.info("client catch exception:{}", cause.getMessage(), cause);
            bus.notify(getExceptionTopic(channelId), Event.wrap(cause));
            ctx.close();
        }
    }
}
