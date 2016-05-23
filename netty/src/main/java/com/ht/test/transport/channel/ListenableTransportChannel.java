package com.ht.test.transport.channel;


/**
 * Created by hutao on 16/5/13.
 * 上午10:53
 */
public interface ListenableTransportChannel extends TransportChannel {
    void registerListener(final TransportChannelListener listener);

    void unRegisterListener(final TransportChannelListener listener);
}
