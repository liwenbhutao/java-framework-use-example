package com.ht.test.common.pool;

import java.util.function.Supplier;


/**
 * Created by hutao on 16/5/13.
 * 下午3:25
 */
public interface PooledObjectSupplier<T extends PooledObject> extends Supplier<T> {
}
