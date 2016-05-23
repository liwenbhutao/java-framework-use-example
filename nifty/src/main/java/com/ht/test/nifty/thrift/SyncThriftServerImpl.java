package com.ht.test.nifty.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

/**
 * Created by hutao on 16/5/6.
 * 下午2:39
 */
@Slf4j
public class SyncThriftServerImpl implements IThriftServer.Iface {
    @Override
    public ThriftResponse request(final ThriftRequest request) throws TException {
        log.info("receive new request:" + request.toString());
        final ThriftResponse thriftResponse = new ThriftResponse(new ThriftResponseHeader(0, "msg", "ext"), "body");
        log.info("write response:" + thriftResponse.toString());
        return thriftResponse;
    }
}
