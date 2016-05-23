package com.ht.test.transport.channel.netty.channel;

import com.ht.test.transport.codec.Codec;
import com.ht.test.transport.protocol.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.RequiredArgsConstructor;

/**
 * Created by hutao on 16/5/6.
 * 上午9:26
 */
@RequiredArgsConstructor
public class MsgEncoder extends MessageToByteEncoder<Msg> {
    private final Codec codec;

    @Override
    protected void encode(final ChannelHandlerContext ctx,
                          final Msg msg,
                          final ByteBuf out) throws Exception {
        final ByteBuf byteBuf = ctx.alloc().directBuffer(msg.getBytesLength());
        try {
            out.writeBytes(codec.encode(msg, byteBuf));
        } finally {
            byteBuf.release();
        }
    }
}
