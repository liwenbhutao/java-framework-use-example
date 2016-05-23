package com.ht.test.transport.channel.netty.registry;

import com.google.common.base.Optional;
import com.ht.test.transport.channel.netty.supporter.ServerCommandHandler;
import com.ht.test.transport.enums.EnumBizType;

/**
 * Created by hutao on 16/5/18.
 * 下午8:30
 */
public interface ServerCommandHandlerRegistry {
    void register(final EnumBizType bizType, final ServerCommandHandler handler);

    Optional<ServerCommandHandler> lookUp(final EnumBizType bizType);

    ServerCommandHandler lookUpWithCheck(final EnumBizType bizType);
}
