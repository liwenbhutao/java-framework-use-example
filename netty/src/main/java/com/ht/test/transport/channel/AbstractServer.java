package com.ht.test.transport.channel;

import com.ht.test.transport.config.ServerNetworkOptions;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Created by hutao on 16/5/13.
 * 下午12:54
 */
public abstract class AbstractServer implements Server {
    @Getter(AccessLevel.PROTECTED)
    private ServerNetworkOptions options;

    public AbstractServer() {
        this(ServerNetworkOptions.DEFAULT);
    }

    public AbstractServer(final ServerNetworkOptions options) {
        this.options = options == null ? ServerNetworkOptions.DEFAULT : options;
    }

    @Override
    public int getPort() {
        return options.getPort();
    }
}
