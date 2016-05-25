package com.ht.test.guice.support;

import com.google.inject.Provider;

/**
 * Created by hutao on 16/5/25.
 * 下午1:58
 */
public class IServiceProvider implements Provider<IService> {
    @Override
    public IService get() {
        return new IService1();
    }
}
