package com.ht.common.spring.util.database;

import com.google.common.base.Strings;
import com.ht.common.spring.util.database.aop.DynamicDataSourcePackageFilterPointcut;
import com.ht.common.spring.util.database.aop.DynamicDataSourceRouterAdvice;
import com.ht.common.spring.util.druid.DruidDataSourceFactory;
import com.ht.common.util.exception.HtPreconditions;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Configuration
@ConditionalOnClass(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ConditionalOnProperty(name = "spring.dynamic.datasource.available",
        havingValue = "true")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties({DynamicDataSourceProperties.class})
@Import(DynamicDataSourceRegistrar.class)
@Slf4j
public class DynamicDataSourceConfiguration {
    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @Primary
    @ConditionalOnMissingBean(AbstractRoutingDataSource.class)
    public DataSource routeDataSource() throws Exception {
        final Map<String, DruidDataSourceFactory> dataSourceMap =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(this.applicationContext, DruidDataSourceFactory.class);
        final Map<Object, Object> targetDataSourceMap = new HashMap<>();

        for (final Map.Entry<String, DruidDataSourceFactory> entry : dataSourceMap.entrySet()) {
            targetDataSourceMap.put(entry.getValue().getDateSourceName(), entry.getValue().getObject());
        }
        HtPreconditions.assertTrue(!dataSourceMap.isEmpty(), "DynamicDataSource is empty");
        final DynamicDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setTargetDataSources(targetDataSourceMap);
        if (!Strings.isNullOrEmpty(this.dynamicDataSourceProperties.getDefaultName())) {
            routingDataSource.setDefaultTargetDataSource(targetDataSourceMap.get(this.dynamicDataSourceProperties.getDefaultName()));
        } else {
            routingDataSource.setDefaultTargetDataSource(targetDataSourceMap.get(this.dynamicDataSourceProperties.getNames().get(0)));
        }
        return routingDataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodInterceptor dataSourceAdvice() {
        return new DynamicDataSourceRouterAdvice();
    }

    @Bean
    @ConditionalOnMissingBean
    public PointcutAdvisor pointcutAdviser() {
        final DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();
        advisor.setPointcut(dataSourcePointcut());
        advisor.setAdvice(dataSourceAdvice());
        return advisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public Pointcut dataSourcePointcut() {
        List<String> basePackages = this.dynamicDataSourceProperties.getBasePackages();
        if (basePackages == null || basePackages.size() == 0) {
            basePackages = AutoConfigurationPackages.get(this.applicationContext);
        }
        return new DynamicDataSourcePackageFilterPointcut(basePackages);
    }
}
