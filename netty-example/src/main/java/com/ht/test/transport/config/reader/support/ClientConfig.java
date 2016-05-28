package com.ht.test.transport.config.reader.support;

import org.aeonbits.owner.Config;

/**
 * Created by hutao on 16/5/23.
 * 上午9:32
 */
@Config.Sources("${configPropertiesFilePath}")
public interface ClientConfig extends Config {
}
