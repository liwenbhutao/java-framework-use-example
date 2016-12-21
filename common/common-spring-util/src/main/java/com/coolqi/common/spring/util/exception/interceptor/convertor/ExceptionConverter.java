package com.coolqi.common.spring.util.exception.interceptor.convertor;

/**
 * @author hutao <hutao, hutao@coolqi.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/11/27
 * @time 上午1:20
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public interface ExceptionConverter {
    /**
     * 是否需要转换
     *
     * @param e
     * @return
     */
    boolean needConvert(final Exception e);

    /**
     * 转换异常
     *
     * @param e
     * @return
     */
    Exception convert(final Exception e);
}
