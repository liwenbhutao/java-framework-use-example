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
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public ServletRegistrationBean druidServlet() {
        final ServletRegistrationBean reg = new ServletRegistrationBean(new StatViewServlet(), "/druid");
        reg.addInitParameter("loginUsername", this.druidProperties.getDruidUsername());
        reg.addInitParameter("loginPassword", this.druidProperties.getDruidPassword());
        return reg;
    }

    @Bean
    public DataSourceProperties properties() {
        return this.druidProperties;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.propertyResolver.getProperty("driver-class-name"));
        druidDataSource.setUrl(this.propertyResolver.getProperty("url"));
        druidDataSource.setUsername(this.propertyResolver.getProperty("username"));
        druidDataSource.setPassword(this.propertyResolver.getProperty("password"));
        if (this.druidProperties.getMaxActive() != null) {
            druidDataSource.setMaxActive(this.druidProperties.getMaxActive());
        }
        if (this.druidProperties.getInitialSize() != null) {
            druidDataSource.setInitialSize(this.druidProperties.getInitialSize());
        }
        if (this.druidProperties.getMinIdle() != null) {
            druidDataSource.setMinIdle(this.druidProperties.getMinIdle());
        }
        if (this.druidProperties.getMaxWait() != null) {
            druidDataSource.setMaxWait(this.druidProperties.getMaxWait());
        }
        if (this.druidProperties.getTimeBetweenConnectErrorMillis() != null) {
            druidDataSource.setTimeBetweenConnectErrorMillis(this.druidProperties.getTimeBetweenConnectErrorMillis());
        }
        if (this.druidProperties.getTimeBetweenEvictionRunsMillis() != null) {
            druidDataSource.setTimeBetweenEvictionRunsMillis(this.druidProperties.getTimeBetweenEvictionRunsMillis());
        }
        if (this.druidProperties.getMinEvictableIdleTimeMillis() != null) {
            druidDataSource.setMinEvictableIdleTimeMillis(this.druidProperties.getMinEvictableIdleTimeMillis());
        }
        if (this.druidProperties.getTestWhileIdle() != null) {
            druidDataSource.setTestWhileIdle(this.druidProperties.getTestWhileIdle());
        }
        if (this.druidProperties.getTestOnBorrow() != null) {
            druidDataSource.setTestOnBorrow(this.druidProperties.getTestOnBorrow());
        }
        if (this.druidProperties.getTestOnReturn() != null) {
            druidDataSource.setTestOnReturn(this.druidProperties.getTestOnReturn());
        }
        if (this.druidProperties.getPoolPreparedStatements() != null) {
            druidDataSource.setPoolPreparedStatements(this.druidProperties.getPoolPreparedStatements());
        }
        if (this.druidProperties.getMaxOpenPreparedStatements() != null) {
            druidDataSource.setMaxOpenPreparedStatements(this.druidProperties.getMaxOpenPreparedStatements());
        }
        if (this.druidProperties.getMaxPoolPreparedStatementPerConnectionSize() != null) {
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(this.druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (this.druidProperties.getQueryTimeout() != null) {
            druidDataSource.setQueryTimeout(this.druidProperties.getQueryTimeout());
        }
        if (this.druidProperties.getTransactionQueryTimeout() != null) {
            druidDataSource.setTransactionQueryTimeout(this.druidProperties.getTransactionQueryTimeout());
        }
        if (!Strings.isNullOrEmpty(this.druidProperties.getValidationQuery())) {
            druidDataSource.setValidationQuery(this.druidProperties.getValidationQuery());
        }

        if (!Strings.isNullOrEmpty(this.druidProperties.getConnectionProperties())) {
            druidDataSource.setConnectionProperties(this.druidProperties.getConnectionProperties());
        }

        if (!Strings.isNullOrEmpty(this.druidProperties.getFilters())) {
            try {
                druidDataSource.setFilters(this.druidProperties.getFilters());
            } catch (final SQLException e) {
                log.error("set druid datasource filter error:{}", e.getMessage(), e);
            }
        }
        return druidDataSource;
    }

    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnClass(Servlet.class)
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid");
        return filterRegistrationBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //mybatis分页
        final PageHelper pageHelper = new PageHelper();
        final Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mybatis/mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}
