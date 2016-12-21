package com.ht.common.spring.util.exception.interceptor.convertor;

import com.google.common.base.Throwables;
import org.springframework.stereotype.Component;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午6:30
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Component
public class CheckExceptionConverter implements ExceptionConverter {
    @Override
    public boolean needConvert(final Exception e) {
        return !(e instanceof RuntimeException);
    }

    @Override
    public Exception convert(final Exception e) {
        return Throwables.propagate(e);
    }
}
