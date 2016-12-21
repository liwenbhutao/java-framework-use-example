package com.ht.common.spring.util.database;

import com.google.common.base.Strings;
import com.ht.common.util.exception.HtPreconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DataSource routeDataSource() {
        final Map<String, DataSource> dataSourceMap =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(this.applicationContext, DataSource.class);
        final Map<Object, Object> targetDataSourceMap = new HashMap<>();

        for (final Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            targetDataSourceMap.put(new DynamicDataSourceContext(entry.getKey()), entry.getValue());
        }
        HtPreconditions.assertTrue(!dataSourceMap.isEmpty(), "DynamicDataSource is empty");
        final DynamicDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setTargetDataSources(targetDataSourceMap);
        if (!Strings.isNullOrEmpty(this.dynamicDataSourceProperties.getDefaultName())) {
            routingDataSource.setDefaultTargetDataSource(dataSourceMap.get(this.dynamicDataSourceProperties.getDefaultName()));
        } else {
            routingDataSource.setDefaultTargetDataSource(dataSourceMap.get(this.dynamicDataSourceProperties.getNames().get(0)));
        }
        return routingDataSource;
    }
}
