package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午10:59
 */
public interface TransportChannelExceptionListener extends TransportChannelListener {
    void onError(final Throwable e, final TransportChannel transportChannel);
}
