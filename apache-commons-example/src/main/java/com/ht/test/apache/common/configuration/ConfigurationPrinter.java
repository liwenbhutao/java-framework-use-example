package com.ht.test.apache.common.configuration;

import com.google.common.base.Throwables;
import lombok.experimental.UtilityClass;
import org.apache.commons.configuration.Configuration;

import java.io.Writer;
import java.util.Iterator;

/**
 * Created by hutao on 16/5/23.
 * 下午6:28
 */
@UtilityClass
public class ConfigurationPrinter {
    public static void printConfiguration(final Configuration configuration,
                                          final Writer writer) {
        try {
            final Iterator<String> iterator = configuration.getKeys();
            while (iterator.hasNext()) {
                final String key = iterator.next();
                writer.write(key + ":" + configuration.getProperty(key).toString() + "\r\n");
            }
            writer.flush();
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
