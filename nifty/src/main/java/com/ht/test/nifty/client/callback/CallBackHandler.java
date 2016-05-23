package com.ht.test.nifty.client.callback;

import com.ht.test.nifty.entity.Response;

/**
 * Created by hutao on 16/5/6.
 * 下午2:45
 */
public interface CallBackHandler {
    void onComplete(final Response response);

    void onError(final Exception e);
}
