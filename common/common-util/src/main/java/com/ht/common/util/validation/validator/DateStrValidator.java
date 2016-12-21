package com.ht.common.util.validation.validator;


import com.google.common.base.Strings;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 验证日期格式是否正确
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
public class DateStrValidator implements ConstraintValidator<DateStr, String> {
    private DateTimeFormatter formatter;
    private boolean allowNullable;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final DateStr dateStr) {
        this.formatter = DateTimeFormat.forPattern(dateStr.pattern());
        this.allowNullable = dateStr.allowNullable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        try {
            if (Strings.isNullOrEmpty(value)) {
                return this.allowNullable;
            }
            this.formatter.parseLocalDateTime(value).toDate();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
}
