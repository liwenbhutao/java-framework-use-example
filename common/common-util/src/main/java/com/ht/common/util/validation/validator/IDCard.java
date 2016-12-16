package com.ht.common.util.validation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 身份证号码
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
@Constraint(validatedBy = IDCardValidator.class)
@Documented
public @interface IDCard {
    String message() default "身份证号码不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
