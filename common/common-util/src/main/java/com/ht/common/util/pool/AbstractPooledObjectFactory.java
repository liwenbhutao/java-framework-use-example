package com.ht.common.util.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description logback过滤掉与dao相关的日志
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午4:44
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Slf4j
public abstract class AbstractPooledObjectFactory<T extends PooledObject> implements PooledObjectFactory<T> {
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
}
