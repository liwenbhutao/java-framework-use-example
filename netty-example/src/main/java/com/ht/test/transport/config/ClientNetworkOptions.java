package com.ht.test.transport.config;

import lombok.Getter;

/**
 * Created by hutao on 16/5/13.
 * 下午3:46
 */
@Getter
public class ClientNetworkOptions<T extends ClientNetworkOptions<T>> extends NetworkOptions<T> {
    public static final int DEFAULT_RECONNECT_ATTEMPTS = 0;
    public static final long DEFAULT_RECONNECT_INTERVAL = 1000L;
    public static final int DEFAULT_CONNECT_TIMEOUT = 60000;
    public static final ClientNetworkOptions DEFAULT = new ClientNetworkOptions();
    private int reconnectAttempts = DEFAULT_RECONNECT_ATTEMPTS;
    private long reconnectInterval = DEFAULT_RECONNECT_INTERVAL;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;

    public T setReconnectInterval(final long reconnectInterval) {
        this.reconnectInterval = reconnectInterval;
        return (T) this;
    }

    public T setReconnectAttempts(final int reconnectAttempts) {
        this.reconnectAttempts = reconnectAttempts;
        return (T) this;
    }

    public T setConnectTimeout(final int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return (T) this;
    }
}
