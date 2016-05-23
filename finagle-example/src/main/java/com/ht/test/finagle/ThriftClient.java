package com.ht.test.finagle;

import com.ht.test.finagle.thrift.Hello;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Function;
import com.twitter.util.Future;
import scala.Function1;
import scala.runtime.BoxedUnit;

/**
 * Created by hutao on 16/5/9.
 * 上午9:41
 */
public final class ThriftClient {

    private ThriftClient() {
    }

    /**
     * Runs the example with given {@code args}.
     *
     * @param args the argument list
     */
    public static void main(String[] args) throws Exception {
        //#thriftclientapi
        Hello.ServiceIface client = Thrift.newIface("localhost:8080", Hello.ServiceIface.class);
        Future<String> response = client.hi().onSuccess(new Function<String, BoxedUnit>() {
            @Override
            public BoxedUnit apply(String response) {
                System.out.println("Received response: " + response);
                return null;
            }
        });
        Await.result(response);
        //final String result = client.hi();
        System.out.println(response);
        //#thriftclientapi
    }
}
