package com.ht.test.reactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by hutao on 16/5/10.
 * 上午11:43
 */
@Slf4j
public class App implements CommandLineRunner {
    public static void main(String[] args) throws Exception {
        new ReactorApp().run();
    }

    /*private static void tcp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        final int port = 8080;
        TcpServer server = TcpServer.create(port);
        TcpClient client = TcpClient.create("localhost", port);


//the client/server are prepared
        server.start(new ChannelHandler<ByteBuf, ByteBuf, NettyChannel>() {
                         @Override
                         public Publisher<Void> apply(NettyChannel nettyChannel) {
                             return nettyChannel.sendOne(PooledByteBufAllocator.DEFAULT.buffer(10).writeInt(1111));
                         }
                     }
        );

        client.start(new ChannelHandler<ByteBuf, ByteBuf, NettyChannel>() {
                         @Override
                         public Publisher<Void> apply(NettyChannel nettyChannel) {
                             return nettyChannel.sendOne(PooledByteBufAllocator.DEFAULT.buffer(10).writeInt(1111));
                         }
                     }

        );

        latch.await(10, TimeUnit.SECONDS);

        client.shutdown();
        server.shutdown();
    }*/


    @Override
    public void run(final String... strings) throws Exception {
    }
}
