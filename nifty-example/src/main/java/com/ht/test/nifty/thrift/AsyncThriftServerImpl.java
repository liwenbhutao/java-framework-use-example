package com.ht.test.nifty.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

/**
 * Created by hutao on 16/5/6.
 * 下午2:40
 */
@Slf4j
public class AsyncThriftServerImpl implements IThriftServer.AsyncIface {
    private final IThriftServer.Iface syncThriftServer;

    public AsyncThriftServerImpl(final IThriftServer.Iface syncThriftServer) {
        this.syncThriftServer = syncThriftServer;
    }

    @Override
    public void request(final ThriftRequest request,
                        final AsyncMethodCallback resultHandler) throws TException {
        resultHandler.onComplete(syncThriftServer.request(request));
    }
}
