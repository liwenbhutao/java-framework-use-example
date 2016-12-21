package com.ht.common.util.pool;

import lombok.experimental.UtilityClass;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

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
@UtilityClass
public class ObjectPoolUtils {
    public static <T extends PooledObject> PooledObjectFactory<T> createPooledObjectFactory(final PooledObjectSupplier<T> supplier) {
        return new BasePooledObjectFactory<>(supplier);
    }

    public static <T extends PooledObject> ObjectPool<T> createGenericObjectPool(final PooledObjectSupplier<T> supplier,
                                                                                 final GenericObjectPoolConfig config) {
        return new GenericObjectPool<>(
                createPooledObjectFactory(supplier),
                config);
    }

    public static <T extends PooledObject> ObjectPool<T> createGenericObjectPool(final PooledObjectSupplier<T> supplier) {
        return createGenericObjectPool(supplier, new GenericObjectPoolConfig());
    }
}
