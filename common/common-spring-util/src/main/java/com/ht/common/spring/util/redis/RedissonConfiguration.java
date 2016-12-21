package com.ht.common.spring.util.redis;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@ConditionalOnMissingBean(RedissonClient.class)
@ConditionalOnProperty(name = "redisson.available", havingValue = "true")
@EnableConfigurationProperties({RedissonProperties.class})
@Slf4j
public class RedissonConfiguration {
    @Autowired
    private RedissonProperties properties;

    @Bean
    @SneakyThrows
    public RedissonClient redissonClient() {
        final RedissonClientFactoryBean factoryBean = new RedissonClientFactoryBean();
        factoryBean.setRedissonProperties(this.properties);
        return factoryBean.createInstance();
    }
}
