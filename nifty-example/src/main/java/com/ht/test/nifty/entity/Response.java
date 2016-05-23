package com.ht.test.nifty.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by hutao on 16/5/6.
 * 上午11:21
 */
@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Response {
    private ResponseHeader header;
    private String body;
}
