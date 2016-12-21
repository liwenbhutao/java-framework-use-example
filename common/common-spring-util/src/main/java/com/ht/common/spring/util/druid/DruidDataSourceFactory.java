package com.ht.common.spring.util.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/18/16
 * @time 10:04 AM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Slf4j
public class DruidDataSourceFactory extends AbstractFactoryBean<DataSource> {
    private final DruidProperties druidProperties;

    public DruidDataSourceFactory(final DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }


    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    protected DataSource createInstance() throws Exception {
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.druidProperties.getDriverClassName());
        druidDataSource.setUrl(this.druidProperties.getUrl());
        druidDataSource.setUsername(this.druidProperties.getUsername());
        druidDataSource.setPassword(this.druidProperties.getPassword());
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
}
