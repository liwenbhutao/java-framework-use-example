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
public class ResponseHeader {
    private static final int HEADER_MIN_LENGTH = 32;
    private static final int EXTENSION_LENGTH = 24;
    private static final int EXTENSION_OFFSET = 8;
    private static final int RET_CODE_OFFSET = 4;
    private static final int RET_CODE_LENGTH = 4;
    private static final int HEAD_LENGTH_OFFSET = 0;
    private static final int HEAD_LENGTH_LENGTH = 4;
    private static final int RET_MSG_OFFSET = 32;
    private static final String EMPTY_RET_MSG = "";
    /**
     * 头部长度
     * 4字节
     */
    private int headLength;
    /**
     * 返回值
     * 4字节
     */
    private int retCode;
    /**
     * 拓展
     * 24字节
     */
    private String extension;
    /**
     * 返回信息
     * 头部长度 - 4 - 4 - 24
     */
    private String retMsg;

    public static ResponseHeader of(final byte[] bytes) {
        Preconditions.checkArgument(bytes.length >= HEADER_MIN_LENGTH);
        final int headLength = NumberUtils.toInt(new String(bytes, HEAD_LENGTH_OFFSET, HEAD_LENGTH_LENGTH));
        final int retCode = NumberUtils.toInt(new String(bytes, RET_CODE_OFFSET, RET_CODE_LENGTH));
        final String extension = new String(bytes, EXTENSION_OFFSET, EXTENSION_LENGTH);
        final int retMsgLength = headLength - HEADER_MIN_LENGTH;
        final String retMsg = retMsgLength > 0 ? new String(bytes, RET_MSG_OFFSET, retMsgLength) : EMPTY_RET_MSG;
        return ResponseHeader.builder()
                .headLength(headLength)
                .retCode(retCode)
                .extension(extension)
                .retMsg(retMsg)
                .build();
    }

    public static ResponseHeader of(final String header) {
        return of(header.getBytes(Charset.forName("utf-8")));
    }
}
