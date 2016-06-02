package com.ht.test.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hutao on 16/5/30.
 * 下午7:21
 */
@Slf4j
public class PullConsumer {
    private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();

    public static void main(String[] args) throws MQClientException {
        final DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumerGroup1");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            consumer.start();

            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("testTopic6");
            for (MessageQueue mq : mqs) {
                final long offset = consumer.fetchConsumeOffset(mq, true);
                putMessageQueueOffset(mq, offset > 0 ? offset : 0);
            }

            while (true) {
                if (mqs.isEmpty()) {
                    log.info("sleep");
                    Thread.sleep(10000);
                } else {
                    int count = 0;

                    for (MessageQueue mq : mqs) {

                        final long messageQueueOffset = getMessageQueueOffset(mq);
                        log.info("Consume from the queue:id[{}],offset[{}]", mq.getQueueId(), messageQueueOffset);
                        PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, messageQueueOffset, 30);
                        log.info("pull result:{}", pullResult);
                        putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                        if (pullResult.getMsgFoundList() != null) {
                            for (final MessageExt messageExt : pullResult.getMsgFoundList()) {
                                consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                                log.info("pull get msg:key{}", messageExt.getKeys());
                            }
                            log.info("pull result count:{}", pullResult.getMsgFoundList().size());
                            count += pullResult.getMsgFoundList().size();
                        }
                        log.info("status:{}", pullResult.getPullStatus());
                    }
                    log.info("pull total msg count:{}", count);
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            consumer.shutdown();
            System.out.println(offseTable);
        }
    }


    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offseTable.put(mq, offset);
    }


    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offseTable.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }
}
