package com.ht.common.util.codec;

import lombok.experimental.UtilityClass;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/30
 * @time 下午8:09
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
public class BytesHexConverter {
    private static final char[] BCD_LOOKUP = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * bytes to hex str
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexStr(final byte[] bytes) {
        final StringBuilder s = new StringBuilder(bytes.length * 2);

        for (final byte b : bytes) {
            s.append(BCD_LOOKUP[(b >>> 4) & 0x0f]);
            s.append(BCD_LOOKUP[b & 0x0f]);
        }

        return s.toString();
    }

    /**
     * hex str to bytes
     *
     * @param s
     * @return
     */
    public static byte[] hexStrToBytes(final String s) {
        final byte[] bytes;

        bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
                    16);
        }

        return bytes;
    }
}
