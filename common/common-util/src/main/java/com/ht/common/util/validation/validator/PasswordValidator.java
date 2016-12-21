package com.ht.common.util.validation.validator;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 校验密码格式
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
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private Password.PwdType pwdType;
    private int minLength;
    private int maxLength;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Password password) {
        this.pwdType = password.pwdType();
        Preconditions.checkArgument(password.minLength() > 0);
        Preconditions.checkArgument(password.maxLength() > 0);
        Preconditions.checkArgument(password.maxLength() >= password.minLength());
        this.minLength = password.minLength();
        this.maxLength = password.maxLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        try {
            if (Strings.isNullOrEmpty(value)) {
                return false;
            }
            //检测空格(不允许有空格)
            if (value.contains(" ")) {
                return false;
            }
            //检测位数
            if (value.length() > this.maxLength || value.length() < this.minLength) {
                return false;
            }
            switch (this.pwdType) {
                case DIGIT:
                    final Matcher isNum = Pattern.compile("[0-9]*").matcher(value);
                    return isNum.matches();
                case DIGIT_WITH_ALPHABET:
                    final Matcher isNumAndAlphabet = Pattern.compile("^[a-zA-Z0-9]*$").matcher(value);
                    return isNumAndAlphabet.matches();
                case ALPHABET:
                    final Matcher isAlphabet = Pattern.compile("^[a-zA-Z]*$").matcher(value);
                    return isAlphabet.matches();
            }
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
}
