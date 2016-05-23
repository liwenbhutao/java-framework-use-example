/*
package com.ht.test.jmeter;

import com.google.common.base.Throwables;
import com.ht.test.transport.protocol.Command;
import com.ht.test.transport.protocol.supporter.CommandSupporter;
import com.ht.test.transport.protocol.Msg;
import com.ht.test.transport.channel.ClientRequestContext;
import com.ht.test.transport.channel.TwiceAsyncClient;
import com.ht.test.transport.config.ClientNetworkOptions;
import com.ht.test.transport.config.NettyClientConfig;
import com.ht.test.transport.channel.netty.MsgTwiceAsyncNettyClient;
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
public class MsgTwiceAsyncNettyClientApp extends AbstractJavaSamplerClient {
    private final TwiceAsyncClient client = new MsgTwiceAsyncNettyClient(
            new ClientNetworkOptions()
                    .setTcpKeepAlive(true)
                    .setReuseAddress(true),
            NettyClientConfig.builder()
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 9999)).build());

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
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            final String commandChannelId = CommandSupporter.generateCommandId();
            final StringBuilder builder = new StringBuilder();
            client.asyncRequestWithTwoResponse(Command.of(commandChannelId + "sdafafdf", "fadfsf"), new TwiceAsyncClient.Listener<Msg>() {
                @Override
                public void onFirstResponse(Msg msg, ClientRequestContext<TwiceAsyncClient> context) {
                    countDownLatch.countDown();
                    builder.append(msg);
                }

                @Override
                public void onSecondResponse(Msg msg, ClientRequestContext<TwiceAsyncClient> context) {
                    countDownLatch.countDown();
                    builder.append(msg);
                }

                @Override
                public void onException(Throwable e, ClientRequestContext<TwiceAsyncClient> context) {
                    throw Throwables.propagate(e);
                }
            });
            try {
                countDownLatch.await();
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
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
