package com.ht.test.finagle;

import com.ht.test.finagle.thrift.Hello;
import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Future;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by hutao on 16/5/9.
 * 上午9:50
 */
public final class ThriftServer {

    private ThriftServer() {
    }

    public static class HelloImpl implements Hello.ServiceIface {
        @Override
        public Future<String> hi() {
            return Future.value("hi");
        }
    }

    /**
     * Runs the example with given {@code args}.
     *
     * @param args the argument list
     */
    public static void main(String[] args) throws Exception {
        //#thriftserverapi
        Hello.ServiceIface impl = new HelloImpl();
        ListeningServer server = Thrift.server().serveIface("localhost:8080", impl);
        Await.ready(server);
        //#thriftserverapi
    }
}
