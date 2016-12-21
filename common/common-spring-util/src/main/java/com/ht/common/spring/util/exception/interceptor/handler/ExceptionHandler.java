package com.ht.common.spring.util.exception.interceptor.handler;

import org.aspectj.lang.ProceedingJoinPoint;

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
public interface ExceptionHandler {
    /**
     * 是否需要处理此异常
     *
     * @param e
     * @return
     */
    boolean needHandle(final Exception e);

    /**
     * 处理异常
     *
     * @param pjp
     * @param e
     * @return
     */
    Object handleException(final ProceedingJoinPoint pjp, final Exception e) throws Exception;
}
