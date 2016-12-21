package com.ht.common.spring.util.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/5
 * @time 下午5:46
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@ConfigurationProperties(prefix = "redisson")
@Data
public class RedissonProperties {
    public static final String DEFAULT_SERVERS = "127.0.0.1:6379";
    /**
     *
     */
    @Value("${servers:}")
    private String servers;
    /**
     * 集群状态扫描间隔时间，单位是毫秒
     */
    @Value("${cluster.scanInterval:}")
    private Integer scanInterval;
    /**
     *
     */
    @Value("${single.connectionPoolSize:}")
    private Integer connectionPoolSize;
    /**
     *
     */
    @Value("${single.subscriptionConnectionPoolSize:}")
    private Integer subscriptionConnectionPoolSize;
    /**
     *
     */
    @Value("${single.subscriptionConnectionMinimumIdleSize:}")
    private Integer subscriptionConnectionMinimumIdleSize;
    /**
     *
     */
    @Value("${single.connectionMinimumIdleSize:}")
    private Integer connectionMinimumIdleSize;
    /**
     *
     */
    @Value("${single.dnsMonitoring:}")
    private Boolean dnsMonitoring;
    /**
     *
     */
    @Value("${single.dnsMonitoringInterval:}")
    private Long dnsMonitoringInterval;
    /**
     *
     */
    @Value("${nettyThreads:}")
    private Integer nettyThreads;
    /**
     *
     */
    @Value("${threads:}")
    private Integer threads;
    /**
     *
     */
    @Value("${useLinuxNativeEpoll:}")
    private Boolean useLinuxNativeEpoll;
    /**
     *
     */
    @Value("${redissonReferenceEnabled:}")
    private Boolean redissonReferenceEnabled;
    /**
     *
     */
    @Value("${mode:SINGLE}")
    private String mode;

    public EnumMode getMode() {
        return EnumMode.valueOf(this.mode);
    }

    enum EnumMode {
        CLUSTER, SINGLE
    }
}
