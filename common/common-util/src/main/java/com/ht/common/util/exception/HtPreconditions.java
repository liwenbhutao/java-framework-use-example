package com.ht.common.util.exception;

import com.ht.common.util.string.StringFormatter;
import lombok.experimental.UtilityClass;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午4:44
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
public class HtPreconditions {
    public static void assertTrue(final boolean expression) {
        if (!expression) {
            throw new HtException();
        }
    }

    public static void assertTrue(final boolean expression,
                                  final String errorMessage) {
        if (!expression) {
            throw new HtException(errorMessage);
        }
    }

    public static void assertTrue(final boolean expression,
                                  final int code,
                                  final String errorMessage) {
        if (!expression) {
            throw new HtCodeException(code, errorMessage);
        }
    }

    public static void assertTrue(final boolean expression,
                                  final String errorMessageTemplate,
                                  final Object... errorMessageArgs) {
        if (!expression) {
            throw new HtException(StringFormatter.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void assertTrue(final boolean expression,
                                  final int code,
                                  final String errorMessageTemplate,
                                  final Object... errorMessageArgs) {
        if (!expression) {
            throw new HtCodeException(code, StringFormatter.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new HtNullException();
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference,
                                     final String errorMessage) {
        if (reference == null) {
            throw new HtNullException(errorMessage);
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, final int code,
                                     final String errorMessage) {
        if (reference == null) {
            throw new HtCodeException(code, errorMessage);
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference,
                                     final String errorMessageTemplate,
                                     final Object... errorMessageArgs) {
        if (reference == null) {
            throw new HtNullException(StringFormatter.format(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }
}
