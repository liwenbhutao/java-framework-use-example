/*
package com.ht.test.jmeter;
import com.google.common.base.Throwables;
import com.ht.test.transport.protocol.Command;
import com.ht.test.transport.protocol.supporter.CommandSupporter;
import com.ht.test.transport.Clients;
import com.ht.test.transport.channel.MultipleAsyncClient;
import io.netty.util.ResourceLeakDetector;
import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

*/
/**
 * Created by hutao on 16/5/6.
 * 上午10:06
 *//*

@Slf4j
public class MultiAsyncClientApp extends AbstractJavaSamplerClient {
    private final MultipleAsyncClient client = Clients.createMultipleAsyncNettyClient(new InetSocketAddress("127.0.0.1", 9999));

    @Override
    public void setupTest(JavaSamplerContext context) {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
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
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            final String commandChannelId = CommandSupporter.generateCommandId();
            final MultipleAsyncClient.MsgChannelId msgChannelId = MultipleAsyncClient.MsgChannelId.of(commandChannelId);
            final StringBuilder builder = new StringBuilder();
            client.openMsgChannel(msgChannelId, new MultipleAsyncClient.Listener() {
                @Override
                public void onResponse(final Object msg,
                                       final MultipleAsyncClient.ClientRequestContextImpl context) {
                    log.debug("msg channel receive msg:{}", msg);
                    countDownLatch.countDown();
                    builder.append("[").append(msg.toString()).append("]");
                }

                @Override
                public void onException(final Throwable e,
                                        final MultipleAsyncClient.ClientRequestContextImpl context) {
                    log.error(e.getMessage(), e);
                }
            });
            client.asyncRequestWithMultipleResponse(msgChannelId, Command.of(commandChannelId + "sdafafdf", "fadfsf"));
            try {
                countDownLatch.await();
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
            client.closeMsgChannel(msgChannelId);
            result.setResponseMessage(builder.toString());
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
