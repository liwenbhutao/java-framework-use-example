package com.ht.test.spring.boot.mvc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hutao <hutao, hutao@email.com>
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
@EnableTransactionManagement
@Slf4j
public class DruidConfiguration implements EnvironmentAware {
    @Autowired
    private DruidProperties druidProperties;
    private Environment environment;
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public ServletRegistrationBean druidServlet() {
        final ServletRegistrationBean reg = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        reg.addInitParameter("loginUsername", druidProperties.getDruidUsername());
        reg.addInitParameter("loginPassword", druidProperties.getDruidPassword());
        return reg;
    }

    @Bean
    public DataSourceProperties properties() {
        return druidProperties;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        druidDataSource.setUrl(propertyResolver.getProperty("url"));
        druidDataSource.setUsername(propertyResolver.getProperty("username"));
        druidDataSource.setPassword(propertyResolver.getProperty("password"));
        if (druidProperties.getMaxActive() != null) {
            druidDataSource.setMaxActive(druidProperties.getMaxActive());
        }
        if (druidProperties.getInitialSize() != null) {
            druidDataSource.setInitialSize(druidProperties.getInitialSize());
        }
        if (druidProperties.getMinIdle() != null) {
            druidDataSource.setMinIdle(druidProperties.getMinIdle());
        }
        if (druidProperties.getMaxWait() != null) {
            druidDataSource.setMaxWait(druidProperties.getMaxWait());
        }
        if (druidProperties.getTimeBetweenConnectErrorMillis() != null) {
            druidDataSource.setTimeBetweenConnectErrorMillis(druidProperties.getTimeBetweenConnectErrorMillis());
        }
        if (druidProperties.getTimeBetweenEvictionRunsMillis() != null) {
            druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        }
        if (druidProperties.getMinEvictableIdleTimeMillis() != null) {
            druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        }
        if (druidProperties.getTestWhileIdle() != null) {
            druidDataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
        }
        if (druidProperties.getTestOnBorrow() != null) {
            druidDataSource.setTestOnBorrow(druidProperties.getTestOnBorrow());
        }
        if (druidProperties.getTestOnReturn() != null) {
            druidDataSource.setTestOnReturn(druidProperties.getTestOnReturn());
        }
        if (druidProperties.getPoolPreparedStatements() != null) {
            druidDataSource.setPoolPreparedStatements(druidProperties.getPoolPreparedStatements());
        }
        if (druidProperties.getMaxOpenPreparedStatements() != null) {
            druidDataSource.setMaxOpenPreparedStatements(druidProperties.getMaxOpenPreparedStatements());
        }
        if (druidProperties.getMaxPoolPreparedStatementPerConnectionSize() != null) {
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (druidProperties.getQueryTimeout() != null) {
            druidDataSource.setQueryTimeout(druidProperties.getQueryTimeout());
        }
        if (druidProperties.getTransactionQueryTimeout() != null) {
            druidDataSource.setTransactionQueryTimeout(druidProperties.getTransactionQueryTimeout());
        }
        if (!Strings.isNullOrEmpty(druidProperties.getValidationQuery())) {
            druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        }

        if (!Strings.isNullOrEmpty(druidProperties.getConnectionProperties())) {
            druidDataSource.setConnectionProperties(druidProperties.getConnectionProperties());
        }

        if (!Strings.isNullOrEmpty(druidProperties.getFilters())) {
            try {
                druidDataSource.setFilters(druidProperties.getFilters());
            } catch (SQLException e) {
                log.error("set druid datasource filter error:{}", e.getMessage(), e);
            }
        }
        return druidDataSource;
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mybatis/mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}
