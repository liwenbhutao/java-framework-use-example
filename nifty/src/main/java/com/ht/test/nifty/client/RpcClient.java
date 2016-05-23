package com.ht.test.nifty.client;

import com.ht.test.nifty.client.callback.CallBackHandler;
import com.ht.test.nifty.entity.Request;
import com.ht.test.nifty.entity.Response;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/6.
 * 下午2:36
 */
public interface RpcClient {
    Response request(final Request request);

    Future<Response> asyncRequest(final Request request);

    void asyncRequest(final Request request, final CallBackHandler callBackHandler);
}
