package com.ht.test.common.pool;

/**
 * Created by hutao on 16/5/13.
 * 下午3:18
 */
public interface PooledObject extends AutoCloseable {
    void active();

    boolean isActive();

    boolean isClosed();
}
