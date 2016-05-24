package com.ht.test.apache.common.beanutils.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * Created by hutao on 16/5/24.
 * 下午1:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private String email;

    private Date birthDate;

    private Address[] addresses;

    private Map<String, String> phone;

    private Map<String, String> telephone;
}
