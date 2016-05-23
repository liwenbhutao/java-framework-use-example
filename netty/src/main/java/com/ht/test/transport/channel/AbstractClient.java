package com.ht.test.transport.channel;

import com.ht.test.transport.config.ClientNetworkOptions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/13.
 * 上午11:27
 */
@Slf4j
public abstract class AbstractClient implements Client {
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private ClientNetworkOptions options;

    public AbstractClient() {
        this(ClientNetworkOptions.DEFAULT);
    }

    public AbstractClient(final ClientNetworkOptions options) {
        this.options = options == null ? ClientNetworkOptions.DEFAULT : options;
    }
}
