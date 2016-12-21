package com.ht.common.util.exception;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午7:20
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class HtNullException extends HtException {
    public HtNullException() {
    }

    public HtNullException(final String message) {
        super(message);
    }

    public HtNullException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public HtNullException(final Throwable cause) {
        super(cause);
    }

    public HtNullException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
