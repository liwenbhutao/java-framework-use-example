package com.coolqi.common.spring.util.manager.service;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import lombok.NonNull;
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
public class UnBlockToBlockServiceDecorator extends AbstractExecutionThreadService {
    private final Runnable runnable;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private final Hook hook;

    public UnBlockToBlockServiceDecorator(@NonNull final Runnable runnable,
                                          final Hook hook) {
        this.runnable = runnable;
        this.hook = hook == null ? NullHook.NULL_HOOK : hook;
    }

    public UnBlockToBlockServiceDecorator(final Runnable runnable) {
        this(runnable, null);
    }

    @Override
    protected void run() throws Exception {
        this.hook.preRun();
        try {
            this.runnable.run();
            this.hook.postRun();
        } catch (final Throwable e) {
            this.hook.handleException(e);
            throw Throwables.propagate(e);
        }
        this.countDownLatch.await();
        this.hook.onShutdown();
    }

    @Override
    protected void triggerShutdown() {
        super.triggerShutdown();
        this.countDownLatch.countDown();
    }

    @Override
    protected void shutDown() throws Exception {
        super.shutDown();
        this.countDownLatch.countDown();
    }

    public interface Hook {
        void preRun();

        void postRun();

        void onShutdown();

        /**
         * @param e
         */
        void handleException(final Throwable e);
    }

    private static class NullHook implements Hook {
        private static final Hook NULL_HOOK = new NullHook();

        @Override
        public void preRun() {

        }

        @Override
        public void postRun() {

        }

        @Override
        public void onShutdown() {

        }

        @Override
        public void handleException(final Throwable e) {
            log.error("service run error:{}", e.getMessage(), e);
        }
    }
}
