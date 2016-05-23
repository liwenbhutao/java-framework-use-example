package com.ht.test.transport.enums;

import com.ht.test.transport.constant.Constants;
import lombok.Getter;

/**
 * Created by hutao on 16/5/11.
 * 下午5:04
 */
@Getter
public enum EnumBizType {
    UTS_A_SHARE(1001, "A股"),
    UTS_B_SHARE(1002, "B股"),;
    private final int id;
    private final String name;
    private final String topic;

    EnumBizType(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.topic = Constants.SERVER_BIZ_TOPIC_PREFIX + id;
    }
}
