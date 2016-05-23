/*
package com.ht.test.jmeter;

import com.ht.test.transport.protocol.Command;
import com.ht.test.transport.Clients;
import com.ht.test.transport.channel.TwiceAsyncClient;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.net.InetSocketAddress;

*/
/**
 * Created by hutao on 16/5/6.
 * 上午10:06
 *//*

public class TwiceAsyncClientApp extends AbstractJavaSamplerClient {
    private final TwiceAsyncClient twiceAsyncNettyClient = Clients.createTwiceAsyncNettyClient(new InetSocketAddress("127.0.0.1", 9999));

    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        twiceAsyncNettyClient.connect();
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
        try {
            twiceAsyncNettyClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SampleResult runTest(final JavaSamplerContext javaSamplerContext) {
        final SampleResult result = new SampleResult();
        result.sampleStart();

        try {
            final TwiceAsyncClient.TwoResponseFuture future = twiceAsyncNettyClient.asyncRequestWithTwoResponse(Command.of("dsafsdfaf", "dsafsff"));
            final Object firstResponse = future.getFirstResponseFuture().get();
            System.out.println(firstResponse);
            final Object secondResponse = future.getSecondResponseFuture().get();
            System.out.println(secondResponse);
            result.setResponseData("first response[" + firstResponse.toString()
                    + "],second response[" + secondResponse.toString() + "]", "utf-8");
            result.setResponseMessageOK();
            result.setSuccessful(true);
        } catch (final Exception e) {
            result.setErrorCount(1);
        }
        result.sampleEnd();
        return result;
    }
}
*/
