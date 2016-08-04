package com.ht.test.brave.profile;

import com.github.kristofa.brave.BraveTracer;
import com.google.common.base.Preconditions;
import com.ht.test.brave.profile.anotation.BraveAnnotation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class BraveTraceAspect implements InitializingBean, DisposableBean {
    @Autowired(required = false)
    private BraveTracer braveTracer;
    private boolean enable = false;
    private final ThreadLocal<Integer> stackDeepCount = new ThreadLocal<>();
    private final ConcurrentHashMap<Class, ConcurrentHashMap<String, BraveAnnotationConfig>> classfiledBraveConfigCacheMap = new ConcurrentHashMap<>();
    private AroundExeMethod exeMethod;

    @Pointcut(value = "execution(* com.ht.test.brave.impl.*.*(..))")
    public void tracePointCut() {
    }

    private int getStackDeepCount() {
        if (this.stackDeepCount.get() == null) {
            this.stackDeepCount.set(0);
        }
        return this.stackDeepCount.get();
    }

    public void setEnable(final boolean enable) {
        this.enable = enable;
        this.exeMethod = enable ? new BraveAroundExeMethod(this.braveTracer) : new NullAroundExeMethod();
    }

    private void increaseStackDeepCount() {
        this.stackDeepCount.set(getStackDeepCount() + 1);
    }

    private void decreaseStackDeepCount() {
        this.stackDeepCount.set(getStackDeepCount() - 1);
    }

    @Around(value = "tracePointCut()")
    @SneakyThrows
    public Object doAround(final ProceedingJoinPoint pjp) {
        return this.exeMethod.doAround(pjp);
    }


    private interface AroundExeMethod {
        Object doAround(final ProceedingJoinPoint pjp);
    }

    private class NullAroundExeMethod implements AroundExeMethod {
        @Override
        @SneakyThrows
        public Object doAround(final ProceedingJoinPoint pjp) {
            final Object[] params = pjp.getArgs();
            return pjp.proceed(params);
        }
    }

    private class BraveAroundExeMethod implements AroundExeMethod {
        private BraveAroundExeMethod(final BraveTracer braveTracer) {
            Preconditions.checkNotNull(braveTracer, "braveTracer can not be null");
            this.braveTracer = braveTracer;
        }

        private final BraveTracer braveTracer;
        private final ThreadLocal<Integer> stackDeepCount = new ThreadLocal<>();
        private final ConcurrentHashMap<Class, ConcurrentHashMap<String, BraveAnnotationConfig>> classfiledBraveConfigCacheMap = new ConcurrentHashMap<>();

        @Override
        @SneakyThrows
        public Object doAround(final ProceedingJoinPoint pjp) {
            final BraveAnnotationConfig config = getBraveAnnotationConfig(pjp);
            if (config.isEnable()) {
                final int stackDeepCount = getStackDeepCount();
                increaseStackDeepCount();
                try {
                    if (stackDeepCount == 0) {
                        this.braveTracer.serverTracer().clearCurrentSpan();
                        this.braveTracer.startServerTracer(config.getName());
                        this.braveTracer.submitBinaryAnnotation("函数入口", config.getFuncName());
                        try {
                            final Object[] params = pjp.getArgs();
                            return pjp.proceed(params);
                        } finally {
                            this.braveTracer.stopServerTracer();
                        }
                    } else {
                        this.braveTracer.startClientTracer(config.getName());
                        this.braveTracer.submitBinaryAnnotation("函数入口", config.getFuncName());
                        try {
                            final Object[] params = pjp.getArgs();
                            return pjp.proceed(params);
                        } finally {
                            this.braveTracer.stopClientTracer();
                        }
                    }
                } finally {
                    decreaseStackDeepCount();
                }
            } else {
                final Object[] params = pjp.getArgs();
                return pjp.proceed(params);
            }
        }

        @SneakyThrows
        private BraveAnnotationConfig getBraveAnnotationConfig(final ProceedingJoinPoint pjp) {
            if (!this.classfiledBraveConfigCacheMap.containsKey(pjp.getSignature().getDeclaringType())) {
                this.classfiledBraveConfigCacheMap.put(pjp.getSignature().getDeclaringType(), new ConcurrentHashMap<>());
            }
            final ConcurrentHashMap<String, BraveAnnotationConfig> filedBraveConfigMap = this.classfiledBraveConfigCacheMap.get(pjp.getSignature().getDeclaringType());
            if (filedBraveConfigMap.containsKey(pjp.getSignature().getName())) {
                return filedBraveConfigMap.get(pjp.getSignature().getName());
            }
            final Object[] params = pjp.getArgs();
            final Class[] paramClass = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramClass[i] = params[i].getClass();
            }
            final BraveAnnotation annotation = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), paramClass).getAnnotation(BraveAnnotation.class);
            final String defaultName = pjp.toString();
            final BraveAnnotationConfig config = annotation == null ? new BraveAnnotationConfig(BraveTraceAspect.this.enable, defaultName, defaultName)
                    : BraveAnnotationConfig.of(annotation, defaultName);
            filedBraveConfigMap.put(pjp.getSignature().getName(), config);
            return config;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setEnable(this.enable);
    }

    @Override
    public void destroy() throws Exception {
    }
}
