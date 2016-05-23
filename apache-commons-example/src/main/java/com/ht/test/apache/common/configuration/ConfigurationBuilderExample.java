package com.ht.test.apache.common.configuration;

import com.google.common.base.Throwables;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

import java.io.Writer;

/**
 * Created by hutao on 16/5/23.
 * 下午6:26
 */
public class ConfigurationBuilderExample {

    private final Configuration configuration;

    public ConfigurationBuilderExample() {
        try {
            final DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder(
                    this.getClass().getResource("/a.properties"));
            configurationBuilder.setEncoding("utf-8");
            configurationBuilder.setProperty("aadfds", "dafsd");
            configuration = configurationBuilder.getConfiguration();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public void println(final Writer writer) {
        ConfigurationPrinter.printConfiguration(configuration, writer);
    }
}
