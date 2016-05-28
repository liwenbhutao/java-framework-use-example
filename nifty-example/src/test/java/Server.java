import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.client.NiftyClient;
import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.NettyServerConfigBuilder;
import com.facebook.nifty.core.NiftyBootstrap;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.facebook.nifty.guice.NiftyModule;
import com.google.inject.Guice;
import com.google.inject.Provider;
import com.google.inject.Stage;
import com.ht.test.nifty.thrift.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.testng.annotations.Test;

import java.net.InetSocketAddress;

/**
 * Created by hutao on 16/5/9.
 * 上午11:20
 */
@Slf4j
public class Server {
    @Test
    public void testName() throws Exception {

        final NiftyBootstrap bootstrap = Guice.createInjector(
                Stage.PRODUCTION,
                new NiftyModule() {
                    @Override
                    protected void configureNifty() {
                        bind().toInstance(new ThriftServerDefBuilder()
                                .listen(8080)
                                .withProcessor(new IThriftServer.Processor<>(request -> {
                                    log.info("receive request:" + request);
                                    final ThriftResponse response = new ThriftResponse();
                                    response.setHeader(new ThriftResponseHeader(0, "ret msg", "extension"));
                                    response.setBody("body");
                                    return response;
                                }))
                                .build()
                        );
                        withNettyServerConfig(NettyConfigProvider.class);
                    }
                }
        ).getInstance(NiftyBootstrap.class);

        bootstrap.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                bootstrap.stop();
            }
        });
        final NiftyClient niftyClient = new NiftyClient();
        IThriftServer.Client client = makeNiftyClient(niftyClient);

        final ThriftRequestHeader requestHeader = new ThriftRequestHeader(1, 2, "extension");
        final ThriftRequest request = new ThriftRequest(requestHeader, "request body");
        final ThriftResponse response = client.request(request);
        log.info("receive response:" + response);
    }

    private IThriftServer.Client makeNiftyClient(final NiftyClient niftyClient)
            throws TTransportException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        FramedClientConnector framedClientConnector = new FramedClientConnector(address);
        TTransport transport = niftyClient.connectSync(IThriftServer.Client.class, framedClientConnector);
        //TTransport transport = niftyClient.connectSync(address);
        TBinaryProtocol tp = new TBinaryProtocol(transport);
        return new IThriftServer.Client(tp);
    }

    public static class NettyConfigProvider implements Provider<NettyServerConfig> {
        @Override
        public NettyServerConfig get() {
            NettyServerConfigBuilder nettyConfigBuilder = new NettyServerConfigBuilder();
            nettyConfigBuilder.getSocketChannelConfig().setTcpNoDelay(true);
            nettyConfigBuilder.getSocketChannelConfig().setConnectTimeoutMillis(5000);
            nettyConfigBuilder.getSocketChannelConfig().setTcpNoDelay(true);
            return nettyConfigBuilder.build();
        }
    }
}
