package com.ht.test.transport.channel.netty.supporter;

import lombok.extern.slf4j.Slf4j;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.bus.registry.Registration;
import reactor.bus.selector.Selector;
import reactor.fn.Consumer;

/**
 * Created by hutao on 16/5/10.
 * 下午8:13
 */
@Slf4j
public abstract class ExceptionListener implements Consumer<Event<Throwable>>, AutoCloseable {
    private Registration<Object, Consumer<? extends Event<?>>> consumerRegistration;

    @Override
    final public void accept(final Event<Throwable> exceptionEvent) {
        doHandle(exceptionEvent.getData());
    }

    protected abstract void doHandle(final Throwable e);

    @Override
    public void close() {
        if (consumerRegistration != null) {
            try {
                consumerRegistration.cancel();
                consumerRegistration = null;
            } catch (Exception e) {
                log.error("unregister event bus consumer error", e);
            }
        }
    }

    public void registerBus(final EventBus bus, final Selector selector) {
        close();
        consumerRegistration = bus.on(selector, this);
    }
}
