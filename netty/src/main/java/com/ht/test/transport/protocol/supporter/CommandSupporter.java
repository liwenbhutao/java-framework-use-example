package com.ht.test.transport.protocol.supporter;

import com.google.common.base.Preconditions;
import com.ht.test.transport.protocol.Command;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

/**
 * Created by hutao on 16/5/10.
 * 下午7:49
 */
@UtilityClass
public class CommandSupporter {
    public final static int COMMAND_ID_LENGTH = 32;

    public static String generateCommandId() {
        final String currentTimestampStr = String.valueOf(new Date().getTime());
        return currentTimestampStr + RandomStringUtils.randomNumeric(COMMAND_ID_LENGTH - currentTimestampStr.length());
    }

    public static Command generateRandomCommand() {
        return Command.of(generateCommandId() + "dsafsdfaf", "dsafsff");
    }

    public static Command generateRandomCommand(final int contentLength) {
        Preconditions.checkArgument(contentLength > 2);
        final int headerContentLength = contentLength >> 2;
        return Command.of(generateCommandId() + RandomStringUtils.randomAlphabetic(headerContentLength), RandomStringUtils.randomAlphabetic(contentLength - headerContentLength));
    }

}
