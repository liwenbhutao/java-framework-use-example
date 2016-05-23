package com.ht.test.apache.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.OutputStreamWriter;

/**
 * Created by hutao on 16/5/23.
 * 下午6:09
 */
@Slf4j
public class CompositeConfigurationExampleTest {
    @Test
    public void should_success_user_composite_configuration() throws Exception {
        final OutputStreamWriter writer = new OutputStreamWriter(System.out);
        new CompositeConfigurationExample().println(writer);
        writer.flush();
    }
}