package com.ht.test.jmeter;

import com.alibaba.rocketmq.common.message.MessageExt;
import com.caimao.uts.common.mq.rocketmq.hanlder.MessageExtHandler;
import com.caimao.uts.gateway.api.message.OrderResponse;
import com.google.common.util.concurrent.SettableFuture;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TDeserializer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 16/6/20.
 *
 * @author hutao
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class ExchangeEntrustRequestHandler implements MessageExtHandler {
    private final TDeserializer tDeserializer = new TDeserializer();
    private final ConcurrentHashMap<String, SettableFuture<OrderResponse>> map;

    /**
     * {@inheritDoc}
     */
    @Override
    @SneakyThrows
    public boolean handle(final MessageExt messageExt) {
        log.info("receive new entrust order[{}] result mq message", messageExt.getKeys());
        final SettableFuture<OrderResponse> future = this.map.get(messageExt.getKeys());
        if (future != null) {
            final OrderResponse response = new OrderResponse();
            this.tDeserializer.deserialize(response, messageExt.getBody());
            future.set(response);
        }
        return true;
    }
}
