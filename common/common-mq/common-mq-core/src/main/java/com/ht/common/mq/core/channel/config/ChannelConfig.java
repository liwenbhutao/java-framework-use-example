package com.ht.common.mq.core.channel.config;

import lombok.Data;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;

import java.util.HashMap;

/**
 * Created by hutao on 16/5/28.
 * 上午11:11
 */
@Data
public class ChannelConfig {
    private final BrokerConfig brokerConfig = new BrokerConfig();
    private final Configuration properties = new MapConfiguration(new HashMap<>());
    private String channelId;

    public void addProperty(final String key, final Object object) {
        properties.addProperty(key, object);
    }
}
