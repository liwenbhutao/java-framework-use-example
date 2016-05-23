package com.ht.test.nifty.client.factory;

import com.ht.test.nifty.client.RpcClient;
import org.apache.commons.pool2.PooledObjectFactory;

/**
 * Created by hutao on 16/5/6.
 * 下午2:52
 */
public interface RpcClientFactory extends PooledObjectFactory<RpcClient> {
}
