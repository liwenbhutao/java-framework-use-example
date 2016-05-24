package com.ht.test.apache.common.beanutils.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hutao on 16/5/24.
 * 下午1:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;

    private String password;

    private String attribute;

    private Long userId;

    private Profile profile;
}
