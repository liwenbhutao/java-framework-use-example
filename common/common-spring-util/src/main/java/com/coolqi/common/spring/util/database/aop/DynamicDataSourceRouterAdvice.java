package com.coolqi.common.spring.util.database.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;

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
public class DynamicDataSourceRouterAdvice implements MethodInterceptor {
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final Method invocationMethod = invocation.getMethod();
        if (log.isDebugEnabled()) {
            log.debug("Methods \"{}\" annotated with @Datasource is start", invocationMethod.toString());
        }
        DynamicDataSourceAopUtil.preMethodProceed(invocationMethod, invocation.getThis().getClass());
        try {
            return invocation.proceed();
        } finally {
            DynamicDataSourceAopUtil.postMethodProceed(invocationMethod, invocation.getThis().getClass());
        }
    }
}
