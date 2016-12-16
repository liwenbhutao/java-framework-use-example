package com.ht.common.util.validation.validator;


import com.coolqi.common.util.constant.RegexPatternConstant;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 验证手机验证码是否正确
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
public class SmsCodeValidator implements ConstraintValidator<SmsCode, String> {
    private int length;
    private boolean allowEmpty;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final SmsCode smsCode) {
        this.length = smsCode.length();
        this.allowEmpty = smsCode.allowEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        try {
            if (Strings.isNullOrEmpty(value)) {
                return this.allowEmpty;
            }
            if (value.length() != this.length) {
                return false;
            }
            return RegexPatternConstant.DIGIT_PATTERN.matcher(value).matches();
        } catch (final Exception e) {
            return false;
        }
    }
}
