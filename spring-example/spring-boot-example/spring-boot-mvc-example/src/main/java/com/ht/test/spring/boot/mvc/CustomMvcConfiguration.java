package com.ht.test.spring.boot.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hutao <胡涛, hutao@email.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/11/27
 * @time 下午3:33
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Configuration
@Slf4j
public class CustomMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
                super.postHandle(request, response, handler, modelAndView);
                log.info("##############111");
            }

            @Override
            public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
                super.afterCompletion(request, response, handler, ex);
                log.info("##############222");
            }

            @Override
            public void afterConcurrentHandlingStarted(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
                super.afterConcurrentHandlingStarted(request, response, handler);
                log.info("##############333");
            }
        });
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

    }
}
