package com.ht.test.apache.common.beanutils.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hutao on 16/5/24.
 * 下午1:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;

    private String city;

    private String addr;

    private String postcode;
}
