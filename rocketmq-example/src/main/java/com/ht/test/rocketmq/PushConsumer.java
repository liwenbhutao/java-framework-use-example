package com.ht.test.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by hutao on 16/5/31.
 * 上午9:49
 */
@Slf4j
public class PushConsumer {
    public static void main(String[] args) {
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    for (final MessageExt messageExt : msgs) {
                        log.info("push get msg:key{}", messageExt.getKeys());
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.subscribe("testTopic5", "*");
            consumer.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            consumer.shutdown();
        }

    }
}
