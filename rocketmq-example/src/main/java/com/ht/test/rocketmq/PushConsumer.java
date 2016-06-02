package com.ht.test.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hutao on 16/5/31.
 * 上午9:49
 */
@Slf4j
public class PushConsumer {
    public static void main(String[] args) {
        final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final AtomicInteger count = new AtomicInteger(0);
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                for (final MessageExt messageExt : msgs) {
                    log.info("push get msg:key{}", messageExt.getKeys());
                    if (count.addAndGet(1) == 65) {
                        countDownLatch.countDown();
                        log.info("un consumer msg:key{}", messageExt.getKeys());
                        throw new RuntimeException("");
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.subscribe("testTopic6", "*");
            consumer.start();
            countDownLatch.await();
            log.info("shutdown");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {

            consumer.shutdown();
        }

    }
}
