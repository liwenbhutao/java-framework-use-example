package com.ht.test.spring.mvc.controller.trade.support;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created on 16/6/21.
 *
 * @author hutao
 * @version 1.0
 */
@Component
public class UtsTradeDeferredResultContainer {
    private final ConcurrentMap<String, UtsTradeDeferredResult> deferredResultConcurrentMap = new ConcurrentHashMap<>();

    public void add(final UtsTradeDeferredResult deferredResult) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(deferredResult.getTradeOrderNo()));
        Preconditions.checkArgument(!this.deferredResultConcurrentMap.containsKey(deferredResult.getTradeOrderNo()));
        deferredResult.onCompletion(() -> remove(deferredResult));
        deferredResult.onTimeout(() -> remove(deferredResult));
        this.deferredResultConcurrentMap.put(deferredResult.getTradeOrderNo(), deferredResult);
    }

    public void remove(final UtsTradeDeferredResult deferredResult) {
        remove(deferredResult.getTradeOrderNo());
    }

    public void remove(final String tradeOrderNo) {
        this.deferredResultConcurrentMap.remove(tradeOrderNo);
    }

    public Optional<UtsTradeDeferredResult> search(final String tradeOrderNo) {
        return Optional.ofNullable(this.deferredResultConcurrentMap.get(tradeOrderNo));
    }
}
