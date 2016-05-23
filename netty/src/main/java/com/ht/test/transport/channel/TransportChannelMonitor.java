package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午11:32
 */
public interface TransportChannelMonitor {
    void registerListener(final TransportChannelLifeListener listener);

    void unRegisterListener(final TransportChannelLifeListener listener);

    void notifyActive(final TransportChannel transportChannel);

    void notifyClose(final TransportChannel transportChannel);

    void notifyError(final TransportChannel transportChannel);

    void notifyReceivedMsg(final Object msg, final TransportChannel transportChannel);
}
