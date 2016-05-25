package com.ht.test.guice.support;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hutao on 16/5/25.
 * 下午1:40
 */
@Slf4j
public class AService1 implements AService {
    @Inject
    private BService bService;
    @Inject
    @Named("CService1")
    private CService cService;
    @Inject
    @AAnotation
    private DService dService1;
    @Inject
    private DService dService2;
    @Inject
    @Named("Integer1")
    private Integer i1;
    @Inject
    @Named("Integer2")
    private Integer i2;
    @Inject
    @AAnotation
    private EService eService1;
    @Inject(optional = true)
    private EService eService2;
    @Inject
    private HService hService;
    @Inject
    private IService iService;

    @Override
    public void run() {
        log.info("bService.func():{}", bService.func());
        log.info("cService.func():{}", cService.func());
        log.info("dService1.func():{}", dService1.func());
        log.info("dService2.func():{}", dService2.func());
        log.info("integer1:{}", i1);
        log.info("integer2:{}", i2);
        log.info("eService1.func():{}", eService1.func());
        log.info("eService2.func():{}", eService2.func());
        log.info("hService.func():{}", hService.func());
        log.info("iService.func():{}", iService.func());
    }
}
