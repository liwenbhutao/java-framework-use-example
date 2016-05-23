package com.ht.test.apache.common.configuration;

import org.testng.annotations.Test;

import java.io.OutputStreamWriter;

/**
 * Created by hutao on 16/5/23.
 * 下午6:32
 */
public class ConfigurationBuilderExampleTest {
    @Test
    public void should_success_user_configuration_builder() throws Exception {
        final OutputStreamWriter writer = new OutputStreamWriter(System.out);
        new ConfigurationBuilderExample().println(writer);
    }
}