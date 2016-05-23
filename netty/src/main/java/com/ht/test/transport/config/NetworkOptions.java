package com.ht.test.transport.config;

import lombok.Getter;

/**
 * Created by hutao on 16/5/13.
 * 下午12:47
 */
@Getter
public class NetworkOptions<T extends NetworkOptions> {
    public static final int DEFAULT_SEND_BUFFER_SIZE = -1;
    public static final int DEFAULT_RECEIVE_BUFFER_SIZE = -1;
    public static final int DEFAULT_TRAFFIC_CLASS = -1;
    public static final boolean DEFAULT_REUSE_ADDRESS = true;
    public static final boolean DEFAULT_TCP_NO_DELAY = true;
    public static final boolean DEFAULT_TCP_KEEP_ALIVE = true;
    public static final int DEFAULT_SO_LINGER = -1;
    public static final boolean DEFAULT_USE_POOLED_BUFFERS = false;
    public static final boolean DEFAULT_SSL = false;
    public static final int DEFAULT_IDLE_TIMEOUT = 0;
    private int sendBufferSize = DEFAULT_SEND_BUFFER_SIZE;
    private int receiveBufferSize = DEFAULT_RECEIVE_BUFFER_SIZE;
    private int trafficClass = DEFAULT_TRAFFIC_CLASS;
    private boolean reuseAddress = DEFAULT_REUSE_ADDRESS;
    private boolean tcpNoDelay = DEFAULT_TCP_NO_DELAY;
    private boolean tcpKeepAlive = DEFAULT_TCP_KEEP_ALIVE;
    private int soLinger = DEFAULT_SO_LINGER;
    private boolean usePooledBuffers = DEFAULT_USE_POOLED_BUFFERS;
    private int idleTimeout = DEFAULT_IDLE_TIMEOUT;
    private boolean ssl = DEFAULT_SSL;

    public T setSendBufferSize(final int sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
        return (T) this;
    }

    public T setReceiveBufferSize(final int receiveBufferSize) {
        this.receiveBufferSize = receiveBufferSize;
        return (T) this;
    }

    public T setTrafficClass(final int trafficClass) {
        this.trafficClass = trafficClass;
        return (T) this;
    }

    public T setReuseAddress(final boolean reuseAddress) {
        this.reuseAddress = reuseAddress;
        return (T) this;
    }

    public T setTcpNoDelay(final boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return (T) this;
    }

    public T setTcpKeepAlive(final boolean tcpKeepAlive) {
        this.tcpKeepAlive = tcpKeepAlive;
        return (T) this;
    }

    public T setSoLinger(final int soLinger) {
        this.soLinger = soLinger;
        return (T) this;
    }

    public T setUsePooledBuffers(final boolean usePooledBuffers) {
        this.usePooledBuffers = usePooledBuffers;
        return (T) this;
    }

    public T setIdleTimeout(final int idleTimeout) {
        this.idleTimeout = idleTimeout;
        return (T) this;
    }

    public T setSsl(final boolean ssl) {
        this.ssl = ssl;
        return (T) this;
    }
}
