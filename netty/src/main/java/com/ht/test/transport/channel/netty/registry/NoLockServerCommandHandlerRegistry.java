package com.ht.test.transport.channel.netty.registry;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.ht.test.transport.channel.netty.supporter.ServerCommandHandler;
import com.ht.test.transport.enums.EnumBizType;

import java.util.Map;

/**
 * Created by hutao on 16/5/18.
 * 下午8:46
 */
public class NoLockServerCommandHandlerRegistry implements ServerCommandHandlerRegistry {
    private final ImmutableMap<EnumBizType, ServerCommandHandler> handlerImmutableMap;

    public NoLockServerCommandHandlerRegistry(final Map<EnumBizType, ServerCommandHandler> handlerMap) {
        handlerImmutableMap = ImmutableMap.<EnumBizType, ServerCommandHandler>builder()
                .putAll(handlerMap)
                .build();
    }

    @Override
    public void register(final EnumBizType bizType, final ServerCommandHandler handler) {
        throw new RuntimeException("no support register command handler by method,support register command handler by constructor");
    }

    @Override
    public Optional<ServerCommandHandler> lookUp(final EnumBizType bizType) {
        return Optional.fromNullable(handlerImmutableMap.get(bizType));
    }

    @Override
    public ServerCommandHandler lookUpWithCheck(final EnumBizType bizType) {
        final ServerCommandHandler serverCommandHandler = handlerImmutableMap.get(bizType);
        Preconditions.checkNotNull(serverCommandHandler);
        return serverCommandHandler;
    }
}
