package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午10:59
 */
public interface TransportChannelLifeListener extends TransportChannelListener {
    void onActive(final TransportChannel transportChannel);

    void onClose(final TransportChannel transportChannel);
}
