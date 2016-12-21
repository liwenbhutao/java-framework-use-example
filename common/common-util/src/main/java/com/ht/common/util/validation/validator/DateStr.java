package com.ht.common.util.validation.validator;


import com.ht.common.util.constant.DatePatternConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 日期字符串
 * @encoding UTF-8
 * @date 2016/11/30
 * @time 下午8:09
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateStrValidator.class)
@Documented
public @interface DateStr {
    String message() default "日期格式不正确。";

    /**
     * 是否允许为空
     *
     * @return
     */
    boolean allowNullable() default true;

    String pattern() default DatePatternConstant.yyyyMMdd_HH_mm_ss;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
