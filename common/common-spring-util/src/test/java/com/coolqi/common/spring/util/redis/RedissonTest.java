package com.coolqi.common.spring.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.concurrent.TimeUnit;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/12/7
 * @time 下午10:00
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@SpringBootTest(classes = RedissonTest.class)
@SpringBootApplication(scanBasePackages = "com.coolqi.common", exclude = {
        DataSourceAutoConfiguration.class,
})
@Slf4j
public class RedissonTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void name() throws Exception {
        final String key = "fsad";
        final RBucket<String> bucket = this.redissonClient.<String>getBucket(key);
        log.info("{}", bucket.trySet("1", 1, TimeUnit.MINUTES));
        log.info("{}", bucket.trySet("1", 1, TimeUnit.MINUTES));
    }
}
