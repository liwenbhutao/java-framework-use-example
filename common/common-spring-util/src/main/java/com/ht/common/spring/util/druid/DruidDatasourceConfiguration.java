package com.ht.common.spring.util.druid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
@ConditionalOnProperty(name = "spring.datasource.type",
        havingValue = "com.alibaba.druid.pool.DruidDataSource")
@EnableConfigurationProperties({DruidProperties.class})
@Slf4j
public class DruidDatasourceConfiguration {
    @Autowired
    private DruidProperties druidProperties;

    @ConditionalOnMissingBean(DataSourceProperties.class)
    @Bean
    public DataSourceProperties properties() {
        return this.druidProperties;
    }

    @ConditionalOnMissingBean(DataSource.class)
    @Bean
    public DruidDataSourceFactory dataSource() {
        return new DruidDataSourceFactory(this.druidProperties);
    }
}
