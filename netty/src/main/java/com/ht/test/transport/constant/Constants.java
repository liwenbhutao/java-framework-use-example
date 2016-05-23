package com.ht.test.transport.constant;

import lombok.experimental.UtilityClass;

/**
 * Created by hutao on 16/5/11.
 * 下午1:34
 */
@UtilityClass
public class Constants {
    public static final int PORT = 8080;
    public static final String SERVER_IP = "127.0.0.1";
    public static final String SERVER_CHANNEL_OUT_TOPIC_PREFIX = "channel_out_";
    public static final String SERVER_BIZ_TOPIC_PREFIX = "biz_";
    public static final String CLIENT_COMMAND_TOPIC_PREFIX = "client_command_";
    public static final String CLIENT_EXCEPTION_TOPIC_PREFIX = "client_exception_";
}
