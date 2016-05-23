package com.ht.test.transport.protocol.supporter;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.charset.Charset;

/**
 * Created by hutao on 16/5/6.
 * 上午11:21
 */
@Getter
@Builder
@ToString
public class RequestHeader {
    private static final int HEADER_LENGTH = 32;
    private static final int EXTENSION_LENGTH = 24;
    /**
     * 业务id
     * 4字节
     */
    private int businessId;
    /**
     * 版本号
     * 4字节
     */
    private int version;
    /**
     * 拓展
     * 24字节
     */
    private String extension;

    public static RequestHeader of(final byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= 32);
        return RequestHeader.builder()
                .businessId(NumberUtils.toInt(new String(bytes, 0, 4)))
                .version(NumberUtils.toInt(new String(bytes, 4, 8)))
                .extension(new String(bytes, 8, EXTENSION_LENGTH))
                .build();
    }

    public static RequestHeader of(final String header) {
        return of(header.getBytes(Charset.forName("utf-8")));
    }
}
