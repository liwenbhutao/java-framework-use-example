package com.ht.test.transport.config.reader.support;

import org.aeonbits.owner.Config;

import java.io.PrintStream;

/**
 * Created by hutao on 16/5/23.
 * 上午9:32
 */
@Config.Sources("${configPropertiesFilePath}")
public interface ServerConfig extends Config {
    @Key("file.separator")
    @DefaultValue("Hello")
    String a();

    void list(PrintStream out);
}
