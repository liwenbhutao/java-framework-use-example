package com.ht.common.mq.core.consumer;

/**
 * Created by hutao on 16/5/28.
 * 上午10:45
 */
public interface PullCallback {
    void onSuccess(final PullResult pullResult);

    void onException(final Throwable e);
}
