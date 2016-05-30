package com.ht.common.mq.core.producer;

import com.ht.common.mq.core.producer.support.SendOption;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/28.
 * 上午10:45
 */
public interface Producer {
    Future<SendResult> send(final ProducerRecord record,
                            final SendOption sendOption);

    void send(final ProducerRecord record,
              final SendOption sendOption,
              final SendCallback sendCallback);
}
