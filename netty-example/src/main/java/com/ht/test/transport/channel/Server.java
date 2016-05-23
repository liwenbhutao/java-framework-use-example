package com.ht.test.transport.channel;

/**
 * Created by hutao on 16/5/13.
 * 上午11:01
 */
public interface Server extends AutoCloseable {
    void start();

    boolean isActive();

    boolean isClosed();

    int getPort();

    enum ServerState {
        ACTIVE, CLOSE
    }
}
