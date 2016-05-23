package com.ht.test.transport.protocol.supporter;

import lombok.experimental.UtilityClass;

/**
 * Created by hutao on 16/5/10.
 * 下午7:46
 */
@UtilityClass
public class CommandHeaderAnalyzer {
    public static String getCommandId(final String header) {
        return header.substring(0, CommandSupporter.COMMAND_ID_LENGTH);
    }
}
