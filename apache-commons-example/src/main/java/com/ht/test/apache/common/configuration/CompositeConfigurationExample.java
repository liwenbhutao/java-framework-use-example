package com.ht.test.apache.common.configuration;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.Writer;

@Slf4j
public class CompositeConfigurationExample {
    private final CompositeConfiguration config;

    public CompositeConfigurationExample() {
        try {
            config = new CompositeConfiguration();
            config.addConfiguration(new PropertiesConfiguration(CompositeConfigurationExample.class.getResource("/a.properties")));
            config.addConfiguration(new PropertiesConfiguration(CompositeConfigurationExample.class.getResource("/b.properties")));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw Throwables.propagate(ex);
        }
    }

    public void println(final Writer writer) {
        ConfigurationPrinter.printConfiguration(config, writer);
    }
}
