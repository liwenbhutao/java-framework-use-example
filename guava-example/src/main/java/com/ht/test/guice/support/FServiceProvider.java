package com.ht.test.guice.support;

import com.google.inject.Provider;

/**
 * Created by hutao on 16/5/25.
 * 下午1:58
 */
public class FServiceProvider implements Provider<FService> {
    @Override
    public FService get() {
        return new FService1();
    }
}
