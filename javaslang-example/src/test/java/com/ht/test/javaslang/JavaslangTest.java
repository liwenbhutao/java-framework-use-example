package com.ht.test.javaslang;

import org.testng.annotations.Test;

/**
 * Created by hutao on 16/5/24.
 * 下午1:35
 */
public class JavaslangTest {
    @Test
    public void run_tuple_test() throws Exception {
        new TupleExample().run();
    }

    @Test
    public void run_function_test() throws Exception {
        new FunctionExample().run();
    }

    @Test
    public void run_values_test() throws Exception {
        new ValuesExample().run();
    }

    @Test
    public void run_collections_test() throws Exception {
        new CollectionsExample().run();
    }
}