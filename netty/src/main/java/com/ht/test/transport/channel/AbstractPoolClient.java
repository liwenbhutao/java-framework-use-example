package com.ht.test.transport.channel;

import com.google.common.base.Throwables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by hutao on 16/5/13.
 * 上午11:49
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractPoolClient extends AbstractClient {
    private GenericObjectPool pool;

    protected final void intPool(final boolean isLazyInit,
                                 final GenericObjectPoolConfig poolConfig,
                                 final PooledObjectFactory<Client> factory) {
        if (pool != null) {
            closePool();
        }
        pool = new GenericObjectPool(factory, poolConfig);


        if (!isLazyInit) {
            for (int i = 0; i < poolConfig.getMaxIdle(); i++) {
                try {
                    pool.addObject();
                } catch (Exception e) {
                    log.error("client init pool add object Error:{}", e.getMessage(), e);
                }
            }
        }
    }

    protected final Client borrowObject() {
        Client client = borrowObjectImpl();

        if (client != null && client.isConnected()) {
            return client;
        }

        invalidateObject(client);

        log.error("client pool borrow client obj error");

        throw new RuntimeException("client pool borrow client obj error");
    }

    private Client borrowObjectImpl() {
        try {
            return (Client) pool.borrowObject();
        } catch (Exception e) {
            log.error("client pool borrow client obj error");
            throw Throwables.propagate(e);
        }
    }

    protected final void invalidateObject(final Client client) {
        if (client == null) {
            return;
        }
        try {
            pool.invalidateObject(client);
        } catch (Exception e) {
            log.error("client pool invalidate client Error: {}", e.getMessage(), e);
        }
    }

    protected final void returnObject(final Client client) {
        if (client == null) {
            return;
        }

        try {
            pool.returnObject(client);
        } catch (Exception e) {
            log.error("client pool return client Error: {}", e.getMessage(), e);
        }
    }

    protected final void closePool() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                log.error("close client pool error:{}", e.getMessage(), e);
            }
            pool = null;
        }
    }

    @Override
    public void close() {
        closePool();
    }
}
