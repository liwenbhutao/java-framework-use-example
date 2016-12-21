package com.coolqi.common.spring.util.druid;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hutao <hutao, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-backend
 * @Description
 * @encoding UTF-8
 * @date 2016/11/27
 * @time 上午9:21
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DruidProperties extends DataSourceProperties {
    @Value("${filters:stat,wall}")
    private String filters;
    @Value("${maxActive:}")
    private Integer maxActive;
    @Value("${initialSize:}")
    private Integer initialSize;
    @Value("${minIdle:}")
    private Integer minIdle;
    @Value("${maxWait:}")
    private Long maxWait;
    @Value("${notFullTimeoutRetryCount:}")
    private Integer notFullTimeoutRetryCount;
    @Value("${validationQuery:}")
    private String validationQuery;
    @Value("${validationQueryTimeout:}")
    private Integer validationQueryTimeout;
    @Value("${testOnBorrow:}")
    private Boolean testOnBorrow;
    @Value("${testOnReturn:}")
    private Boolean testOnReturn;
    @Value("${testWhileIdle:}")
    private Boolean testWhileIdle;
    @Value("${poolPreparedStatements:}")
    private Boolean poolPreparedStatements;
    @Value("${sharePreparedStatements:}")
    private Boolean sharePreparedStatements;
    @Value("${maxPoolPreparedStatementPerConnectionSize:}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${inited:}")
    private Boolean inited;
    @Value("${queryTimeout:}")
    private Integer queryTimeout;
    @Value("${transactionQueryTimeout:}")
    private Integer transactionQueryTimeout;
    @Value("${createTimespan:}")
    private Long createTimespan;
    @Value("${maxWaitThreadCount:}")
    private Integer maxWaitThreadCount;
    @Value("${accessToUnderlyingConnectionAllowed:}")
    private Boolean accessToUnderlyingConnectionAllowed;
    @Value("${timeBetweenEvictionRunsMillis:}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${numTestsPerEvictionRun:}")
    private Integer numTestsPerEvictionRun;
    @Value("${minEvictableIdleTimeMillis:}")
    private Long minEvictableIdleTimeMillis;
    @Value("${maxEvictableIdleTimeMillis:}")
    private Long maxEvictableIdleTimeMillis;
    @Value("${phyTimeoutMillis:}")
    private Long phyTimeoutMillis;
    @Value("${removeAbandoned:}")
    private Boolean removeAbandoned;
    @Value("${removeAbandonedTimeoutMillis:}")
    private Long removeAbandonedTimeoutMillis;
    @Value("${logAbandoned:}")
    private Boolean logAbandoned;
    @Value("${maxOpenPreparedStatements:}")
    private Integer maxOpenPreparedStatements;
    @Value("${timeBetweenConnectErrorMillis:}")
    private Long timeBetweenConnectErrorMillis;
    @Value("${connectionProperties:}")
    private String connectionProperties;
}
