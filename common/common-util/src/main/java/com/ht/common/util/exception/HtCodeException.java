package com.ht.common.util.exception;

import lombok.Getter;

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
public class HtCodeException extends HtException {
    @Getter
    private int code;

    public HtCodeException(final int code) {
        super();
        this.code = code;
    }

    public HtCodeException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public HtCodeException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public HtCodeException(final int code, final Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String format() {
        return "code:" + this.code + ",msg:" + getMessage();
    }
}
