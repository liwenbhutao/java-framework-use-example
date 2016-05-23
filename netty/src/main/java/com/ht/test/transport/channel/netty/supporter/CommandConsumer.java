package com.ht.test.transport.channel.netty.supporter;

import com.ht.test.transport.protocol.Command;
import lombok.extern.slf4j.Slf4j;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by hutao on 16/5/10.
 * 下午8:13
 */
@Slf4j
public abstract class CommandConsumer implements Consumer<Event<Command>> {
    @Override
    final public void accept(final Event<Command> commandEvent) {
        doHandle(commandEvent.getData());
    }

    protected abstract void doHandle(final Command command);
}
