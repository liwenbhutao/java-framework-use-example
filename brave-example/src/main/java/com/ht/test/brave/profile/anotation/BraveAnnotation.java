package com.ht.test.brave.profile.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BraveAnnotation {
    String name() default "";

    boolean enable() default true;
}