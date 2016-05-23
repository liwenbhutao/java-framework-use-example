package com.ht.test.transport.config.reader;

import lombok.experimental.UtilityClass;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

/**
 * Created by hutao on 16/5/23.
 * 上午9:31
 */
@UtilityClass
public class ConfigReader {
    /**
     * configPropertiesFilePath can be such as
     * "file:*.properties" "classpath:*.properties" "http://somewhere.com/*.properties"
     *
     * @param tClass
     * @param configPropertiesFilePath
     * @param <T>
     * @return
     */
    public synchronized static <T extends Config> T loadConfig(final Class<T> tClass,
                                                               final String configPropertiesFilePath) {
        ConfigFactory.setProperty("configPropertiesFilePath", configPropertiesFilePath);
        return ConfigFactory.create(tClass);
    }
}
