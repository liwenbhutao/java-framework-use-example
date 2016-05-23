package com.ht.test.transport.protocol;

import io.netty.buffer.ByteBuf;

/**
 * Created by hutao on 16/5/19.
 * 下午3:23
 */
public interface Msg<T extends Msg> {
    Header getHeader();

    String getHeaderStr();

    default String getHeaderIdStr() {
        return getHeader().getIdStr();
    }

    String getBodyStr();

    int getBytesLength();

    T decodeFromBuf(final ByteBuf outBuf);

    ByteBuf encodeToBuf(final ByteBuf inBuf);

    interface Header {
        String getIdStr();
    }
}
