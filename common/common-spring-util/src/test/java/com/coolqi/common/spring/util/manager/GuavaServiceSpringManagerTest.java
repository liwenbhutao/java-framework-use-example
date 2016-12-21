package com.coolqi.common.spring.util.manager;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/15/16
 * @time 4:39 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@SpringBootTest(classes = GuavaServiceSpringManagerTest.class)
//@ComponentScan(basePackages = {"com.coolqi.common.spring.util.manager", "com.coolqi.common.spring.util.context"})
@Configuration
@Slf4j
public class GuavaServiceSpringManagerTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private GuavaServiceSpringManager guavaServiceSpringManager;

    @Test
    public void name() throws Exception {
        this.guavaServiceSpringManager.toString();
        System.out.println();
    }

    @Bean
    public Service aService() {
        return new AbstractExecutionThreadService() {
            @Override
            protected void run() throws Exception {
                log.info("a service run");
            }
        };
    }
}