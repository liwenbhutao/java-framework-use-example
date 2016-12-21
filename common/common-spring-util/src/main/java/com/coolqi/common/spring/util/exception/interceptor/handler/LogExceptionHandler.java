package com.coolqi.common.spring.util.exception.interceptor.handler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

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
@Component
@Slf4j
public class LogExceptionHandler implements ExceptionHandler {
    @Setter
    private boolean open = true;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean needHandle(final Exception e) {
        return this.open;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object handleException(final ProceedingJoinPoint pjp,
                                  final Exception e) throws Exception {
        log.error("log interceptor catch exception at [{}]", pjp.toString()
                + ",error:" + e.getMessage(), e);
        throw e;
    }
}
