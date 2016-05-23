/*
package com.ht.test.jmeter;

import com.ht.test.transport.Clients;
import com.ht.test.transport.channel.RpcClient;
import com.ht.test.transport.protocol.Msg;
import com.ht.test.transport.protocol.supporter.CommandSupporter;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.net.InetSocketAddress;

*/
/**
 * Created by hutao on 16/5/6.
 * 上午10:06
 *//*

public class RpcClientApp extends AbstractJavaSamplerClient {
    private final RpcClient<Msg> client = Clients.createRpcNettyClient(new InetSocketAddress("127.0.0.1", 9999));

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        client.connect();
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
        final SampleResult result = new SampleResult();
        result.sampleStart();
        try {
            final Msg response = client.request(CommandSupporter.generateRandomCommand());
            result.setResponseData(response.toString(), "utf-8");
            result.setResponseMessageOK();
            result.setSuccessful(true);
        } catch (final Exception e) {
            result.setSuccessful(false);
            result.setErrorCount(1);
        }
        result.sampleEnd();
        return result;
    }
}
*/
