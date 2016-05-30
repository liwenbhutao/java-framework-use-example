package com.ht.common.mq.core.consumer.support;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by hutao on 16/5/28.
 * 下午6:03
 */
@Getter
@ToString
public class PullOption {
    public static final int DEFAULT_TIMEOUT = -1;
    public static final int DEFAULT_MAX_NUM = -1;
    public static final int DEFAULT_OFFSET = -1;
    private long timeout = DEFAULT_TIMEOUT;
    private int maxNum = DEFAULT_MAX_NUM;
    private long offset = DEFAULT_OFFSET;

    public PullOption setOffset(long offset) {
        this.offset = offset;
        return this;
    }

    public PullOption setMaxNum(int maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    public PullOption setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }
}
