package com.ht.common.mq.core.channel.config;

import lombok.Data;

/**
 * Created by hutao on 16/5/28.
 * 下午5:23
 */
@Data
public class BrokerConfig {
    private String brokerAddress;
    private String username;
    private String password;
}
