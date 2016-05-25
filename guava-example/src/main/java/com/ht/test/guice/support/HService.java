package com.ht.test.guice.support;

import com.google.inject.ImplementedBy;

/**
 * Created by hutao on 16/5/25.
 * 下午1:46
 */
@ImplementedBy(HService1.class)
public interface HService {
    String func();
}
