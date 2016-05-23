package com.ht.test.common.pool;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/13.
 * 下午3:26
 */
@Slf4j
public class BasePooledObjectFactory<T extends PooledObject> extends AbstractPooledObjectFactory<T> {
    private final PooledObjectSupplier<T> objectFactory;

    public BasePooledObjectFactory(final PooledObjectSupplier<T> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    protected T doMakeObject() {
        return objectFactory.get();
    }
}
