package com.ht.common.spring.util.redis;

import com.google.common.base.Strings;
import com.ht.common.util.exception.HtPreconditions;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/5
 * @time 下午5:40
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class RedissonClientFactoryBean extends AbstractFactoryBean<RedissonClient> {
    @Setter
    private RedissonProperties redissonProperties;

    @Override
    protected RedissonClient createInstance() throws Exception {
        final Config config = new Config();
        if (this.redissonProperties != null) {
            switch (this.redissonProperties.getMode()) {
                case SINGLE:
                    final SingleServerConfig singleServerConfig = config.useSingleServer();
                    singleServerConfig.setAddress(Strings.isNullOrEmpty(this.redissonProperties.getServers()) ? RedissonProperties.DEFAULT_SERVERS : this.redissonProperties.getServers());
                    if (this.redissonProperties.getConnectionMinimumIdleSize() != null) {
                        singleServerConfig.setConnectionMinimumIdleSize(this.redissonProperties.getConnectionMinimumIdleSize());
                    }
                    if (this.redissonProperties.getSubscriptionConnectionMinimumIdleSize() != null) {
                        singleServerConfig.setSubscriptionConnectionMinimumIdleSize(this.redissonProperties.getSubscriptionConnectionMinimumIdleSize());
                    }
                    if (this.redissonProperties.getSubscriptionConnectionPoolSize() != null) {
                        singleServerConfig.setSubscriptionConnectionPoolSize(this.redissonProperties.getSubscriptionConnectionPoolSize());
                    }
                    if (this.redissonProperties.getDnsMonitoring() != null) {
                        singleServerConfig.setDnsMonitoring(this.redissonProperties.getDnsMonitoring());
                    }
                    if (this.redissonProperties.getDnsMonitoringInterval() != null) {
                        singleServerConfig.setDnsMonitoringInterval(this.redissonProperties.getDnsMonitoringInterval());
                    }
                    if (this.redissonProperties.getConnectionPoolSize() != null) {
                        singleServerConfig.setConnectionPoolSize(this.redissonProperties.getConnectionPoolSize());
                    }
                    break;
                case CLUSTER:
                    final ClusterServersConfig clusterServersConfig = config.useClusterServers();
                    HtPreconditions.assertTrue(!Strings.isNullOrEmpty(this.redissonProperties.getServers()));
                    final String[] serverArray = StringUtils.split(this.redissonProperties.getServers(), ';');
                    HtPreconditions.assertTrue(serverArray.length > 1);
                    clusterServersConfig.addNodeAddress(serverArray);
                    if (this.redissonProperties.getScanInterval() != null) {
                        clusterServersConfig.setScanInterval(this.redissonProperties.getScanInterval());
                    }
                    break;
            }
        }
        if (this.redissonProperties.getNettyThreads() != null) {
            config.setNettyThreads(this.redissonProperties.getNettyThreads());
        }
        if (this.redissonProperties.getRedissonReferenceEnabled() != null) {
            config.setRedissonReferenceEnabled(this.redissonProperties.getRedissonReferenceEnabled());
        }

        if (this.redissonProperties.getUseLinuxNativeEpoll() != null) {
            config.setUseLinuxNativeEpoll(this.redissonProperties.getUseLinuxNativeEpoll());
        }

        if (this.redissonProperties.getThreads() != null) {
            config.setThreads(this.redissonProperties.getThreads());
        }

        return Redisson.create(config);
    }

    @Override
    public Class<?> getObjectType() {
        return RedissonClient.class;
    }
}
