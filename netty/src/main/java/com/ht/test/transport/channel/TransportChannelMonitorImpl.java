package com.ht.test.transport.channel;

import io.netty.util.internal.ConcurrentSet;

/**
 * Created by hutao on 16/5/13.
 * 上午11:33
 */
public class TransportChannelMonitorImpl implements TransportChannelMonitor {
    private final ConcurrentSet<TransportChannelLifeListener> listenerSet = new ConcurrentSet<>();

    @Override
    public void registerListener(final TransportChannelLifeListener listener) {
        synchronized (listenerSet) {
            listenerSet.add(listener);
        }
    }

    @Override
    public void unRegisterListener(final TransportChannelLifeListener listener) {
        synchronized (listenerSet) {
            listenerSet.remove(listener);
        }
    }

    @Override
    public void notifyActive(final TransportChannel transportChannel) {
        for (final TransportChannelLifeListener listener : listenerSet) {
            listener.onActive(transportChannel);
        }
    }

    @Override
    public void notifyClose(final TransportChannel transportChannel) {
        for (final TransportChannelLifeListener listener : listenerSet) {
            listener.onClose(transportChannel);
        }
    }

    @Override
    public void notifyError(final TransportChannel transportChannel) {
        for (final TransportChannelLifeListener listener : listenerSet) {
            //listener.onError(transportChannel);
        }
    }

    @Override
    public void notifyReceivedMsg(final Object msg, final TransportChannel transportChannel) {
        for (final TransportChannelLifeListener listener : listenerSet) {
            //listener.onReceivedMsg(msg, transportChannel);
        }
    }
}
