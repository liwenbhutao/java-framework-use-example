package com.ht.common.mq.core.producer.support;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hutao on 16/5/30.
 * 上午9:47
 */
@Getter
@ToString
public class SendOption {
    public static final int DEFAULT_TIMEOUT = -1;
    private long timeout = DEFAULT_TIMEOUT;

    public SendOption setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }
}
