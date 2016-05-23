package com.ht.test.transport.channel;

import java.net.InetSocketAddress;

/**
 * Created by hutao on 16/5/13.
 * 下午1:41
 */
public interface ClientTransportChannel extends ListenableTransportChannel {
    void connect(final InetSocketAddress remoteAddress);

    boolean isConnected();
}
