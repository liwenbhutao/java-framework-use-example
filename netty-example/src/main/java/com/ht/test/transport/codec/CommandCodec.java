package com.ht.test.transport.codec;

import com.ht.test.transport.constant.CharsetConstant;
import com.ht.test.transport.protocol.Command;
import io.netty.buffer.ByteBuf;

/**
 * Created by hutao on 16/5/10.
 * 上午9:33
 */
public class CommandCodec implements Codec<Command> {
    @Override
    public Command decode(final ByteBuf byteBuf) {
        final int headerBytes = byteBuf.readInt();
        final String header = byteBuf.readBytes(headerBytes).toString(CharsetConstant.UTF8);
        final String body = byteBuf.toString(CharsetConstant.UTF8);
        return Command.builder()
                .headerBytes(headerBytes)
                .headerStr(header)
                .body(body)
                .build();
    }

    @Override
    public ByteBuf encode(final Command command, final ByteBuf byteBuf) {
        return byteBuf.writeInt(command.getHeaderBytes())
                .writeBytes(command.getHeaderStr().getBytes(CharsetConstant.UTF8))
                .writeBytes(command.getBody().getBytes(CharsetConstant.UTF8));

    }
}
