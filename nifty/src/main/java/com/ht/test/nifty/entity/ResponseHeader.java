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
public class ResponseHeader {
    /**
     * 返回值
     */
    private int retCode;
    /**
     * 拓展
     */
    private String extension;
    /**
     * 返回信息
     */
    private String retMsg;
}
