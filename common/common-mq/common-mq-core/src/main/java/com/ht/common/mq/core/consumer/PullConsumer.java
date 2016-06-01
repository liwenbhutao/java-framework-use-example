package com.ht.common.mq.core.consumer;

import com.ht.common.mq.core.consumer.support.PullOption;

/**
 * Created by hutao on 16/5/28.
 * 上午10:57
 */
public interface PullConsumer extends Consumer {
    PullResult pull(final PullOption pullOption);
}
