package com.ht.test.transport.channel;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/13.
 * 下午4:11
 */
public interface RpcClient<T> extends Client {
    Future<T> asyncRequest(final T msg);

    void asyncRequest(final T msg, final Listener<T> listener);

    T request(final T msg);

    interface Listener<T> {
        void onResponse(final T msg, final ClientRequestContext<RpcClient> context);

        void onException(final Throwable e, final ClientRequestContext<RpcClient> context);
    }
}
