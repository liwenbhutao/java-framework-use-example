package com.ht.test.transport.channel.netty.channel;

import com.ht.test.transport.codec.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by hutao on 16/5/6.
 * 上午9:26
 */
@RequiredArgsConstructor
public class MsgDecoder extends ByteToMessageDecoder {
    private final Codec codec;

    @Override
    protected void decode(final ChannelHandlerContext channelHandlerContext,
                          final ByteBuf byteBuf,
                          final List<Object> list) throws Exception {
        final int dataLength = byteBuf.readableBytes();
        if (dataLength > 0) {
            final ByteBuf directBuffer = channelHandlerContext.alloc().directBuffer(dataLength);
            try {
                final ByteBuf byteBufCopy = directBuffer.writeBytes(byteBuf, dataLength);
                list.add(codec.decode(byteBufCopy));
            } finally {
                directBuffer.release();
            }
        }
    }
}
