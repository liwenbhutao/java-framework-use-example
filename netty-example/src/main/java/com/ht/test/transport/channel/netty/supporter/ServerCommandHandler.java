package com.ht.test.transport.channel.netty.supporter;

import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by hutao on 16/5/18.
 * 下午8:28
 */
public abstract class ServerCommandHandler implements Consumer<Event<ServerCommand>> {
    @Override
    final public void accept(final Event<ServerCommand> serverCommandEvent) {
        doHandle(serverCommandEvent.getData());
    }

    protected abstract void doHandle(final ServerCommand serverCommand);
}
