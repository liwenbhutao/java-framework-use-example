package com.ht.common.util.validation.validator;

import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 验证手机号格式是否正确
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
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private String regexp;
    private boolean allowEmpty;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Phone constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
        this.allowEmpty = constraintAnnotation.allowEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(value)) {
            return this.allowEmpty;
        }
        final Pattern p = Pattern.compile(this.regexp);
        final Matcher m = p.matcher(value);
        return m.matches();
    }
}
