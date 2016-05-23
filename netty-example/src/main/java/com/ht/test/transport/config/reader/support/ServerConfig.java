package com.ht.test.transport.config.reader.support;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;

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
