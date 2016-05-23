package com.ht.test.transport.channel.netty.supporter;

import com.ht.test.transport.protocol.Command;
import lombok.Data;

/**
 * Created by hutao on 16/5/11.
 * 下午4:44
 */
@Data
public class ServerCommand {
    private final Command command;
    private final ServerChannelContext serverChannelContext;
}
