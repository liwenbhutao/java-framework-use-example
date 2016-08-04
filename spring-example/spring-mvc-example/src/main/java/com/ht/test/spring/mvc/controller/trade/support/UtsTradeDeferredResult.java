package com.ht.test.spring.mvc.controller.trade.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created on 16/6/21.
 *
 * @author hutao
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class UtsTradeDeferredResult extends DeferredResult<TradeResult> {
    /**
     * UTS交易订单号(委托单号,撤销委托单号)
     */
    @Getter
    private final String tradeOrderNo;

    public UtsTradeDeferredResult(final String tradeOrderNo) {
        this.tradeOrderNo = tradeOrderNo;
    }

    public UtsTradeDeferredResult(final String tradeOrderNo,
                                  final Long timeout) {
        super(timeout);
        this.tradeOrderNo = tradeOrderNo;
    }

    public UtsTradeDeferredResult(final String tradeOrderNo,
                                  final Long timeout,
                                  final Object timeoutResult) {
        super(timeout, timeoutResult);
        this.tradeOrderNo = tradeOrderNo;
    }
}
