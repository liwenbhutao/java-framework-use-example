package com.ht.common.mq.core.producer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.MapConfiguration;

import java.util.HashMap;

/**
 * Created by hutao on 16/5/30.
 * 上午9:29
 */
@RequiredArgsConstructor(staticName = "of")
public class ProducerRecord {
    private final String topic;
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
