package com.ht.test.transport.config.reader.support;

import com.google.common.io.Resources;
import com.ht.test.transport.config.reader.ConfigReader;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.loaders.PropertiesLoader;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * Created by hutao on 16/5/23.
 * 上午9:38
 */
public class ServerConfigTest {
    @Test
    public void testName() throws Exception {
        final ServerConfig config = ConfigReader.loadConfig(ServerConfig.class, "classpath:a.properties");
        config.list(System.out);
    }

    @Test
    public void testName1() throws Exception {
        final ServerConfig config = ConfigReader.loadConfig(ServerConfig.class, "file:/Users/hutao/Downloads/a.properties");
        config.list(System.out);

    }

    @Test
    public void testName2() throws Exception {
        final Properties properties = new Properties();
        new PropertiesLoader().load(properties, Resources.getResource("a.properties").toURI());
        //properties.load(this.getClass().getResourceAsStream("a.properties"));
        ConfigFactory.create(ServerConfig.class, properties).list(System.out);
    }
}