package com.coolqi.common.spring.util.database.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 11:09 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Aspect
@Slf4j
public class DynamicDataSourceRouterAspect {
    /**
     */
    @Pointcut("@within(DynamicDataSourceAnnotation)")
    public void classAnnotationPointcut() {
    }

    /**
     */
    @Pointcut("@annotation(DynamicDataSourceAnnotation)")
    public void methodAnnotationPointcut() {
    }

    /**
     */
    @Pointcut("classAnnotationPointcut() || methodAnnotationPointcut()")
    public void dataSource() {
    }

    @Around("dataSource()")
    public Object invoke(final ProceedingJoinPoint point) throws Throwable {
        log.debug("Methods \"{}\" @Datasource is start", point.toLongString());
        final Method method = findExecutedMethod(point);
        DynamicDataSourceAopUtil.preMethodProceed(method, point.getThis().getClass());
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceAopUtil.postMethodProceed(method, point.getThis().getClass());
        }
    }

    private Method findExecutedMethod(final ProceedingJoinPoint point)
            throws NoSuchMethodException {
        final MethodSignature methodSignature =
                (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            final String methodName = methodSignature.getName();
            method = point.getTarget().getClass()
                    .getDeclaredMethod(methodName, method.getParameterTypes());
        }
        return method;
    }
}
