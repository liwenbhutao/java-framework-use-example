package com.ht.test.apache.common.cli;

import org.testng.annotations.Test;

/**
 * Created by hutao on 16/5/26.
 * 下午5:19
 */
public class CliExampleTest {

    @Test
    public void run_cli_example() throws Exception {
        new CliExample(new String[]{"-h"}).run();
        new CliExample(new String[]{"-g dfasdf"}).run();
    }
}