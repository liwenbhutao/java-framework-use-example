package com.ht.common.util.validation.validator;


import com.ht.common.util.math.DoubleUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 检查double精度
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
public class DoubleValidator implements ConstraintValidator<Double, java.lang.Double> {
    private int accuracy;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Double d) {
        this.accuracy = d.accuracy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final java.lang.Double value,
                           final ConstraintValidatorContext context) {
        try {
            if (this.accuracy > -1) {
                return DoubleUtils.getAccuracy(value) <= this.accuracy;
            }
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
}
