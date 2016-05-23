package com.ht.test.nifty.client.impl;

import com.ht.test.nifty.client.RpcClient;
import com.ht.test.nifty.client.callback.CallBackHandler;
import com.ht.test.nifty.entity.Request;
import com.ht.test.nifty.entity.Response;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/6.
 * 下午2:48
 */
public class ThriftRpcClient implements RpcClient {
    @Override
    public Response request(final Request request) {
        return null;
    }

    @Override
    public Future<Response> asyncRequest(final Request request) {
        return null;
    }

    @Override
    public void asyncRequest(final Request request,
                             final CallBackHandler callBackHandler) {

    }
}
