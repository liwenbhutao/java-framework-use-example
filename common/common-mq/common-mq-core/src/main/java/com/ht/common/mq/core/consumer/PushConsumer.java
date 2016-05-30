package com.ht.common.mq.core.consumer;

/**
 * Created by hutao on 16/5/28.
 * 上午10:57
 */
public interface PushConsumer extends Consumer {
    void subscribe(final String topic);

    void unsubscribe(final String topic);
}
