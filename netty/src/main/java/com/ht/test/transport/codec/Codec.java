package com.ht.test.transport.codec;

import io.netty.buffer.ByteBuf;

/**
 * Created by hutao on 16/5/6.
 * 上午9:41
 */
public interface Codec<T> {
    T decode(final ByteBuf byteBuf);

    ByteBuf encode(final T msg, final ByteBuf byteBuf);
}
