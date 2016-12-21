package com.coolqi.common.spring.util.manager.service;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

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
@Slf4j
public abstract class AbstractUnBlockToBlockService extends AbstractExecutionThreadService {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    @Setter
    private String serviceName = this.getClass().getName();

    @Override
    final protected void run() throws Exception {
        preRun();
        try {
            doRun();
            postRun();
        } catch (final Throwable e) {
            handleException(e);
            throw Throwables.propagate(e);
        }
        this.countDownLatch.await();
        onShutdown();
    }

    protected void onShutdown() {
        log.info("success to shutdown service[{}]", this.serviceName);
    }

    protected void handleException(final Throwable e) {
        log.error("service[" + this.serviceName + "] start error:{}", e.getMessage(), e);
    }

    protected void postRun() {
        log.info("success to start service[{}]", this.serviceName);
    }


    protected void preRun() {
        log.info("begin to start service[{}]", this.serviceName);
    }

    protected abstract void doRun() throws Exception;

    @Override
    protected void triggerShutdown() {
        super.triggerShutdown();
        this.countDownLatch.countDown();
    }

    @Override
    protected void shutDown() throws Exception {
        super.shutDown();
        this.countDownLatch.countDown();
        doStop();
    }

    protected void doStop() {

    }
}
