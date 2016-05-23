package com.ht.test.transport.channel.netty.registry;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.ht.test.transport.channel.netty.supporter.ServerCommandHandler;
import com.ht.test.transport.enums.EnumBizType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hutao on 16/5/18.
 * 下午8:46
 */
public class ConcurrentServerCommandHandlerRegistry implements ServerCommandHandlerRegistry {
    private final ConcurrentMap<EnumBizType, ServerCommandHandler> commandHandlerConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void register(final EnumBizType bizType,
                         final ServerCommandHandler handler) {
        commandHandlerConcurrentMap.put(bizType, handler);
    }

    @Override
    public Optional<ServerCommandHandler> lookUp(final EnumBizType bizType) {
        return Optional.fromNullable(commandHandlerConcurrentMap.get(bizType));
    }

    @Override
    public ServerCommandHandler lookUpWithCheck(final EnumBizType bizType) {
        final ServerCommandHandler serverCommandHandler = commandHandlerConcurrentMap.get(bizType);
        Preconditions.checkNotNull(serverCommandHandler);
        return serverCommandHandler;
    }
}
