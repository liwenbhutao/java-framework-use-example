package com.ht.common.spring.util.database.aop;

import com.google.common.base.Strings;
import com.ht.common.spring.util.database.DynamicDataSourceContextHolder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 9:33 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
@Slf4j
public class DynamicDataSourceAopUtil {
    private final static ConcurrentMap<Method, DynamicDataSourceAnnotation> cacheMap = new ConcurrentHashMap<>();

    public static DynamicDataSourceAnnotation findDynamicDataSourceAnnotation(final Method method, final Class<?> targetClass) {
        final DynamicDataSourceAnnotation cacheAnnotation = cacheMap.get(method);
        if (cacheAnnotation != null) {
            return cacheAnnotation;
        }
        final DynamicDataSourceAnnotation annotation = internalFindDynamicDataSourceAnnotation(method, targetClass);
        if (annotation != null) {
            cacheMap.put(method, annotation);
        }
        return annotation;
    }

    private static DynamicDataSourceAnnotation internalFindDynamicDataSourceAnnotation(final Method method, final Class<?> targetClass) {
        final boolean isDebugEnabled = log.isDebugEnabled();
        DynamicDataSourceAnnotation annotation;
        if ((annotation = AnnotationUtils.findAnnotation(method, DynamicDataSourceAnnotation.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Method {}.{} has @DataSource(value={})", targetClass.getSimpleName(), method.getName(), annotation.value());
            }
            return annotation;
        }

        final Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if ((annotation = AnnotationUtils.findAnnotation(specificMethod, DynamicDataSourceAnnotation.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Specific Method {}.{} has @DataSource(value={})", targetClass.getSimpleName(), method.getName(), annotation.value());
            }
            return annotation;
        }

        if ((annotation = AnnotationUtils.findAnnotation(targetClass, DynamicDataSourceAnnotation.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Class {} has @DataSource(value={})", targetClass.getSimpleName(), annotation.value());
            }
            return annotation;
        }
        if ((annotation = AnnotationUtils.findAnnotation(targetClass.getPackage(), DynamicDataSourceAnnotation.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Package {} has @DataSource(value={})", targetClass.getPackage().toString(), annotation.value());
            }
            return annotation;
        }
        return null;
    }

    public static String getDynamicDataSourceName(final Method method, final Class<?> targetClass) {
        final DynamicDataSourceAnnotation annotation = findDynamicDataSourceAnnotation(method, targetClass);
        return annotation == null ? "" : annotation.value();
    }

    public static void preMethodProceed(final Method method, final Class<?> targetClass) {
        final String dynamicDataSourceName = DynamicDataSourceAopUtil.getDynamicDataSourceName(method, method.getDeclaringClass());
        if (!Strings.isNullOrEmpty(dynamicDataSourceName)) {
            final String currentDataSourceName = DynamicDataSourceContextHolder.getDynamicDataSourceName();
            if (!dynamicDataSourceName.equals(currentDataSourceName)) {
                DynamicDataSourceContextHolder.setDynamicDataSourceName(dynamicDataSourceName);
                log.debug("'{}' change datasource", dynamicDataSourceName);
            }
        }
    }

    public static void postMethodProceed(final Method method, final Class<?> targetClass) {
        DynamicDataSourceContextHolder.clearDynamicDataSourceName();
        if (log.isDebugEnabled()) {
            log.debug("Methods \"{}\" annotated with @Datasource is end", method.toGenericString());
        }
    }
}
