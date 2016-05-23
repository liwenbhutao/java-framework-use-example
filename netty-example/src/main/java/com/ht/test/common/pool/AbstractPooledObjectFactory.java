package com.ht.test.common.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by hutao on 16/5/13.
 * 下午3:20
 */
@Slf4j
public abstract class AbstractPooledObjectFactory<T extends PooledObject> implements PooledObjectFactory<T>, AutoCloseable {
    /*private GenericObjectPool pool;*/

    /*protected final void intPool(final boolean isLazyInit,
                                 final GenericObjectPoolConfig poolConfig) {
        if (pool != null) {
            closePool();
        }
        pool = new GenericObjectPool(this, poolConfig);


        if (!isLazyInit) {
            for (int i = 0; i < poolConfig.getMaxIdle(); i++) {
                try {
                    pool.addObject();
                } catch (Exception e) {
                    log.error("pooled obj factory init pool add object Error:{}", e.getMessage(), e);
                }
            }
        }
    }*/

    @Override
    public org.apache.commons.pool2.PooledObject<T> makeObject() throws Exception {
        return new DefaultPooledObject<>(doMakeObject());
    }

    protected abstract T doMakeObject();

    @Override
    public void destroyObject(final org.apache.commons.pool2.PooledObject<T> p) {
        final T object = p.getObject();
        try {
            if (!object.isClosed()) {
                object.close();
            }
        } catch (final Exception e) {
            log.error("obj pool destroy object error:{}", e.getMessage(), e);
        }
    }

    @Override
    public boolean validateObject(final org.apache.commons.pool2.PooledObject<T> p) {
        return p.getObject().isActive();
    }

    @Override
    public void activateObject(final org.apache.commons.pool2.PooledObject<T> p) {
        final T object = p.getObject();
        if (!object.isActive()) {
            object.active();
        }
    }

    @Override
    public void passivateObject(final org.apache.commons.pool2.PooledObject<T> p) {

    }

    /*protected final void closePool() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                log.error("close client pool error:{}", e.getMessage(), e);
            } finally {
                pool = null;
            }
        }
    }*/

    @Override
    public void close() {
    }
}
