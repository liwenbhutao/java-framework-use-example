package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午10:58
 */
public interface Client extends AutoCloseable {
    void connect();

    boolean isConnected();

    boolean isClosed();
}
