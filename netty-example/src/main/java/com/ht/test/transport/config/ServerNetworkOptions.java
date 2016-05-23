package com.ht.test.transport.config;

import lombok.Getter;

/**
 * Created by hutao on 16/5/13.
 * 下午3:46
 */
@Getter
public class ServerNetworkOptions<T extends ServerNetworkOptions> extends NetworkOptions<T> {
    public static final int DEFAULT_PORT = 0;
    public static final String DEFAULT_HOST = "0.0.0.0";
    public static final int DEFAULT_ACCEPT_BACKLOG = -1;
    public static ServerNetworkOptions DEFAULT = new ServerNetworkOptions();
    private int port = DEFAULT_PORT;
    private String host = DEFAULT_HOST;
    private int acceptBacklog = DEFAULT_ACCEPT_BACKLOG;

    public T setPort(final int port) {
        this.port = port;
        return (T) this;
    }

    public T setHost(final String host) {
        this.host = host;
        return (T) this;
    }

    public T setAcceptBacklog(final int acceptBacklog) {
        this.acceptBacklog = acceptBacklog;
        return (T) this;
    }
}
