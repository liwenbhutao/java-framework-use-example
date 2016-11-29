package com.ht.test.spring.boot.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
/*
        final WebRequestInterceptor interceptor = new Han() {
            @Override
            public void preHandle(WebRequest webRequest) throws Exception {
                log.info("##############333");
            }

            @Override
            public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
                log.info("##############222");
            }

            @Override
            public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {
                log.info("##############333");
            }
        };
*/
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                super.postHandle(request, response, handler, modelAndView);
                log.info("##############111");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                super.afterCompletion(request, response, handler, ex);
                log.info("##############222");
            }

            @Override
            public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                super.afterConcurrentHandlingStarted(request, response, handler);
                log.info("##############333");
            }
        });
    }
}
