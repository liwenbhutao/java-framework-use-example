package com.ht.common.spring.util.manager;

import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 12/15/16
 * @time 4:34 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Component
@Slf4j
public class GuavaServiceSpringManager implements InitializingBean, DisposableBean, ApplicationContextAware {
    private ServiceManager serviceManager;
    @Setter
    private List<Service> serviceList = new ArrayList<>();
    @Setter
    private boolean loadServiceListFromApplicationContext = true;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        if (this.loadServiceListFromApplicationContext) {
            final Map<String, Service> beansOfType = applicationContext.getBeansOfType(Service.class);
            for (final Map.Entry<String, Service> entry : beansOfType.entrySet()) {
                log.info("add service[{}] load from ApplicationContext to guava service manager", entry.getKey());
                this.serviceList.add(entry.getValue());
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!this.serviceList.isEmpty()) {
            this.serviceManager = new ServiceManager(this.serviceList);
            this.serviceManager.startAsync();
        }
        log.info("guava service manager start");
    }

    @Override
    public void destroy() throws Exception {
        if (this.serviceManager != null) {
            this.serviceManager.stopAsync();
            this.serviceManager.awaitStopped();
            log.info("guava service manager shutdown");
        }
    }
}

