package com.ht.test.transport.channel.netty.channel;

import com.ht.test.transport.codec.Codec;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * Created by hutao on 16/5/6.
 * 上午9:27
 */
public class MsgChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ChannelHandler handler;
    private final Codec codec;

    public MsgChannelInitializer(final ChannelHandler handler, final Codec codec) {
        this.handler = handler;
        this.codec = codec;
    }

    @Override
    protected void initChannel(final SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        socketChannel.pipeline().addLast("objectDecoder", new MsgDecoder(codec));

        socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
        socketChannel.pipeline().addLast("objectEncoder", new MsgEncoder(codec));

        socketChannel.pipeline().addLast(handler);
    }
}
