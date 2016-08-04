package com.ht.test.spring.mvc.controller.trade.support.mq.handler;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.caimao.uts.common.mq.rocketmq.hanlder.MessageExtHandler;
import com.caimao.uts.gateway.api.message.OrderCancelResponse;
import com.google.common.base.Strings;
import com.ht.test.spring.mvc.controller.trade.support.TradeResult;
import com.ht.test.spring.mvc.controller.trade.support.UtsTradeDeferredResult;
import com.ht.test.spring.mvc.controller.trade.support.UtsTradeDeferredResultContainer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created on 16/6/20.
 *
 * @author hutao
 * @version 1.0
 */
@Component
@Slf4j
public class ExchangeEntrustRevokeRequestHandler implements MessageExtHandler {
    @Autowired
    private UtsTradeDeferredResultContainer utsTradeDeferredResultContainer;
    private final TDeserializer tDeserializer = new TDeserializer();
    private final ConcurrentMap<String, UtsTradeDeferredResult> deferredResultConcurrentMap = new ConcurrentHashMap<>();


    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public boolean handle(final MessageExt messageExt) {
        final Optional<UtsTradeDeferredResult> deferredResult = this.utsTradeDeferredResultContainer.search(messageExt.getKeys());
        if (deferredResult.isPresent()) {
            final OrderCancelResponse response = new OrderCancelResponse();
            this.tDeserializer.deserialize(response, messageExt.getBody());
            final UtsTradeDeferredResult tradeDeferredResult = deferredResult.get();
            tradeDeferredResult.setResult(new TradeResult(Strings.nullToEmpty(response.header.code),
                    Strings.nullToEmpty(response.header.info)));
            this.utsTradeDeferredResultContainer.remove(tradeDeferredResult);
        }
        return true;
    }
}
