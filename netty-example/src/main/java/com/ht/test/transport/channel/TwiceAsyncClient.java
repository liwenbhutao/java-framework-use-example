package com.ht.test.transport.channel;

import com.google.common.util.concurrent.SettableFuture;
import com.ht.test.transport.protocol.Msg;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/13.
 * 下午4:11
 */
public interface TwiceAsyncClient<T extends Msg> extends Client {
    TwoResponseFuture asyncRequestWithTwoResponse(final T msg);

    void asyncRequestWithTwoResponse(final T msg, final Listener<T> callbackHandler);

    interface Listener<T> {
        void onFirstResponse(final T msg, final ClientRequestContext<TwiceAsyncClient> context);

        void onSecondResponse(final T msg, final ClientRequestContext<TwiceAsyncClient> context);

        void onException(final Throwable e, final ClientRequestContext<TwiceAsyncClient> context);
    }

    class TwoResponseFuture<T> {
        private final SettableFuture<T> firstResponseFuture;
        private final SettableFuture<T> secondResponseFuture;

        public TwoResponseFuture() {
            this.firstResponseFuture = SettableFuture.create();
            this.secondResponseFuture = SettableFuture.create();
        }

        public Future<T> getFirstResponseFuture() {
            return firstResponseFuture;
        }

        public Future<T> getSecondResponseFuture() {
            return secondResponseFuture;
        }
    }
}
