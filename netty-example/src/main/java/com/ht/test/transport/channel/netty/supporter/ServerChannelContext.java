package com.ht.test.transport.channel.netty.supporter;

import com.ht.test.transport.protocol.Command;
import lombok.Builder;
import lombok.Getter;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.registry.Registration;
import reactor.fn.Consumer;

import java.util.List;

/**
 * Created by hutao on 16/5/11.
 * 下午4:27
 */
@Getter
@Builder
public class ServerChannelContext {
    private String channelId;
    private String channelOutTopic;
    private EventBus outBus;
    private Registration<Object, Consumer<? extends Event<?>>> consumerRegistration;

    public void addResponseCommandToBus(final Command command) {
        outBus.notify(channelOutTopic, Event.wrap(command));
    }

    public void addResponseCommandToBus(final List<Command> commandList) {
        commandList.forEach(this::addResponseCommandToBus);
    }
}
