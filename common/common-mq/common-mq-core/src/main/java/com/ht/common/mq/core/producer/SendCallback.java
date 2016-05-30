package com.ht.common.mq.core.producer;

/**
 * Created by hutao on 16/5/28.
 * 上午10:45
 */
public interface SendCallback {
    void onSuccess(final SendResult sendResult);


    void onException(final Throwable e);
}
