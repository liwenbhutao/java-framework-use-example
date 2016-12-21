package com.ht.common.spring.util.druid;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 10:15 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Configuration
@ConditionalOnProperty(name = "spring.servlet.druid.available",
        havingValue = "true")
public class DruidServletConfiguration {
    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public ServletRegistrationBean druidServlet(@Value("${spring.servlet.druid.username:admin}") final String admin,
                                                @Value("${spring.servlet.druid.password:admin123}") final String password) {
        final ServletRegistrationBean reg = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        reg.addInitParameter("loginUsername", admin);
        reg.addInitParameter("loginPassword", password);
        return reg;
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
