package com.ht.test.transport.protocol;

import com.ht.test.transport.constant.CharsetConstant;
import com.ht.test.transport.protocol.supporter.CommandHeaderAnalyzer;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hutao on 16/5/6.
 * 上午11:21
 */
@ToString
@Builder
@Getter
@AllArgsConstructor
public class Command implements Msg<Command> {
    private int headerBytes;
    private String headerStr;
    private String body;

    public Command() {
    }


    public static Command of(final String header, final String body) {
        return Command.builder()
                .headerBytes(header.getBytes(CharsetConstant.UTF8).length)
                .headerStr(header)
                .body(body)
                .build();
    }

    public int getBytesLength() {
        return Integer.BYTES + headerBytes + body.getBytes(CharsetConstant.UTF8).length;
    }

    @Override
    public Header getHeader() {
        return () -> CommandHeaderAnalyzer.getCommandId(headerStr);
    }

    @Override
    public String getBodyStr() {
        return body;
    }

    @Override
    public Command decodeFromBuf(final ByteBuf outBuf) {
        headerBytes = outBuf.readInt();
        headerStr = outBuf.readBytes(headerBytes).toString(CharsetConstant.UTF8);
        body = outBuf.toString(CharsetConstant.UTF8);
        return this;
    }

    @Override
    public ByteBuf encodeToBuf(final ByteBuf inBuf) {
        return inBuf.writeInt(headerBytes)
                .writeBytes(headerStr.getBytes(CharsetConstant.UTF8))
                .writeBytes(body.getBytes(CharsetConstant.UTF8));
    }
}
