package com.ht.test.transport.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by hutao on 16/5/13.
 * 下午5:21
 */
@AllArgsConstructor
@Getter
public class ClientRequestContext<T extends Client> {
    private final T client;
}
