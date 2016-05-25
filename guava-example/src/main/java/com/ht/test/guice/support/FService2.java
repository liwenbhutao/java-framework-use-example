package com.ht.test.guice.support;

import com.google.inject.Singleton;

/**
 * Created by hutao on 16/5/25.
 * 下午1:58
 */
@Singleton
public class FService2 implements FService {
    @Override
    public String func() {
        return "FService2 func";
    }
}
