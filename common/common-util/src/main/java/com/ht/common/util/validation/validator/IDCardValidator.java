package com.ht.common.util.validation.validator;


import com.google.common.base.Strings;
import com.ht.common.util.validation.IdCardUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 验证身份证格式是否正确
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
public class IDCardValidator implements ConstraintValidator<IDCard, String> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final IDCard idCard) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        try {
            if (Strings.isNullOrEmpty(value)) {
                return false;
            }
            return IdCardUtils.IDCardValidate(value);
        } catch (final Exception e) {
            return false;
        }
    }
}
