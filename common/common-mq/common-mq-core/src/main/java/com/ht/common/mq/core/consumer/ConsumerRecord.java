package com.ht.common.mq.core.consumer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;

import java.util.HashMap;

/**
 * Created by hutao on 16/5/30.
 * 上午9:29
 */
public class ConsumerRecord {
    private Configuration properties;

    public Configuration getProperties() {
        if (properties == null) {
            properties = new MapConfiguration(new HashMap<>());
        }
        return properties;
    }

    public void addProperty(final String key, final Object object) {
        properties.addProperty(key, object);
    }
}
