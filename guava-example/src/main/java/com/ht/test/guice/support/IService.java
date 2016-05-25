package com.ht.test.guice.support;

import com.google.inject.ProvidedBy;

/**
 * Created by hutao on 16/5/25.
 * 下午1:46
 */
@ProvidedBy(IServiceProvider.class)
public interface IService {
    String func();
}
