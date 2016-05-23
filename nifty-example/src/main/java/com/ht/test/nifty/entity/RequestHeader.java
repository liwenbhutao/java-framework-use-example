package com.ht.test.nifty.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hutao on 16/5/6.
 * 上午11:21
 */
@Getter
@Builder
@ToString
public class RequestHeader {
    /**
     * 业务id
     */
    private int businessId;
    /**
     * 版本号
     */
    private int version;
    /**
     * 拓展
     */
    private String extension;
}
