package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午10:59
 */
public interface TransportChannelMsgListener extends TransportChannelListener {
    void onReceivedMsg(final Object msg, final TransportChannel transportChannel);
}
