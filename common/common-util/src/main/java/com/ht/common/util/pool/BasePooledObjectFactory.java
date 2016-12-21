package com.ht.common.util.pool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
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
public class BasePooledObjectFactory<T extends PooledObject> extends AbstractPooledObjectFactory<T> {
    private final PooledObjectSupplier<T> objectFactory;

    public BasePooledObjectFactory(final PooledObjectSupplier<T> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    protected T doMakeObject() {
        return this.objectFactory.get();
    }
}
