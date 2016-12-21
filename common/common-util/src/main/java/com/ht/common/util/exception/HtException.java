package com.ht.common.util.exception;

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
public class HtException extends RuntimeException {
    public HtException() {
        super("");
    }

    public HtException(final String message) {
        super(message);
    }

    public HtException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public HtException(final Throwable cause) {
        super(cause);
    }

    public HtException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


