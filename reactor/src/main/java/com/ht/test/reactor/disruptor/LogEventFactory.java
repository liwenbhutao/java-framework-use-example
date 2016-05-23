package com.ht.test.reactor.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by hutao on 16/5/10.
 * 下午3:46
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent("log");
    }
}
