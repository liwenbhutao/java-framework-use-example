package com.coolqi.common.spring.util.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午4:25
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Component
public final class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 返回spring上下文
     *
     * @return spring上下文
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * get bean object
     *
     * @return bean object
     */
    public static <T> T getBean(final String beanName, final Class<T> tClass) {
        return getContext().getBean(beanName, tClass);
    }

    /**
     * 设置spring上下午
     *
     * @param context spring上下文
     * @throws BeansException
     */
    @SuppressWarnings("static-access")
    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
