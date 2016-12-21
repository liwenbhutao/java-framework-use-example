package com.ht.common.spring.util.exception.interceptor.convertor;

import lombok.Setter;
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
public class ExceptionConverterAdvice {
    @Setter
    private ExceptionConverter exceptionConverter;

    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (final Exception e) {
            if (this.exceptionConverter.needConvert(e)) {
                throw this.exceptionConverter.convert(e);
            }
            throw e;
        }
    }
}
