package com.ht.common.spring.util.aop;

import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 11:24 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class AnnotationDeepMethodMatcher extends AnnotationMethodMatcher {
    private final Class<? extends Annotation> annotationType;

    public AnnotationDeepMethodMatcher(final Class<? extends Annotation> annotationType) {
        super(annotationType);
        this.annotationType = annotationType;
    }

    @Override
    public boolean matches(final Method method, final Class<?> targetClass) {
        if (super.matches(method, targetClass)) {
            return true;
        }

        if (AnnotationUtils.findAnnotation(targetClass, this.annotationType) != null) {
            return true;
        }

        if (AnnotationUtils.findAnnotation(targetClass.getPackage(), this.annotationType) != null) {
            return true;
        }

        return false;
    }
}
