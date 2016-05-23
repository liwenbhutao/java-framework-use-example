package com.ht.test.transport.channel;


import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/13.
 * 上午10:53
 */
public interface TransportChannel {
    InetSocketAddress getLocalAddress();

    InetSocketAddress getRemoteAddress();

    Future<Void> writeMsg(final Object msg);

    void close();

    boolean isActive();

    boolean isClosed();
}
