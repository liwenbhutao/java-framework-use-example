package com.ht.test.apache.common.beanutils;

import com.ht.test.apache.common.beanutils.support.Address;
import com.ht.test.apache.common.beanutils.support.Profile;
import com.ht.test.apache.common.beanutils.support.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hutao on 16/5/24.
 * 下午1:45
 */
@Slf4j
public class BeanUtilsExample implements Runnable {
    @Override
    public void run() {

        try {
            User user = prepareDate();
            log.info(BeanUtils.getProperty(user, "userId"));
            log.info(PropertyUtils.getProperty(user, "userId").toString());
            log.info(BeanUtils.getProperty(user, "username"));
            log.info(BeanUtils.getProperty(user, "password"));
            log.info(BeanUtils.getProperty(user, "attribute"));
            log.info(BeanUtils.getProperty(user, "profile"));
            log.info(BeanUtils.getProperty(user, "profile.email"));
            System.out
                    .println(BeanUtils.getProperty(user, "profile.birthDate"));
            log.info(BeanUtils.getProperty(user, "profile.phone"));
            log.info(BeanUtils.getProperty(user,
                    "profile.phone(mobile)"));
            log.info(BeanUtils.getProperty(user,
                    "profile.phone(office)"));
            log.info(BeanUtils.getProperty(user,
                    "profile.phone(home)"));
            log.info(BeanUtils.getProperty(user,
                    "profile.addresses[0].country"));
            log.info(BeanUtils.getProperty(user,
                    "profile.addresses[0].city"));
            log.info(BeanUtils.getProperty(user,
                    "profile.addresses[0].postcode"));


            User user2 = new User();
            BeanUtils.copyProperties(user2, user); // 將user這物件轉至user2
            log.info(BeanUtils.getProperty(user2, "userId")); // 輸出user類的userId的值
            log.info(PropertyUtils.getProperty(user2, "userId").toString());
            log.info(BeanUtils.getProperty(user2, "username"));
            log.info(BeanUtils.getProperty(user2, "password"));
            log.info(BeanUtils.getProperty(user2, "profile"));
            log.info(BeanUtils.getProperty(user2, "profile.email"));
            log.info(BeanUtils
                    .getProperty(user2, "profile.birthDate"));
            log.info(BeanUtils.getProperty(user2, "profile.phone"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.phone(mobile)"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.phone(office)"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.phone(home)"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.addresses[0].country"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.addresses[0].city"));
            log.info(BeanUtils.getProperty(user2,
                    "profile.addresses[0].postcode"));

            System.out
                    .println("--------------------------------------------------------------------");

            Profile profile = new Profile();
            HashMap<String, String> map = new HashMap<>();

            map.put("1", "13880808080");
            map.put("2", "13550505050");
            BeanUtils.setProperty(profile, "telephone", map);
            log.info(BeanUtils.getProperty(profile, "telephone(1)"));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private User prepareDate() {
        Profile profile = new Profile();

        profile.setEmail("pp@gmail.com");
        profile.setBirthDate(new GregorianCalendar(2012, 6, 16).getTime());

        Map<String, String> phone = new HashMap<>();
        phone.put("home", "(03)XXXXXXX");
        phone.put("office", "(02)1234-5678");
        phone.put("mobile", "09XX-XXX-XXX");
        profile.setPhone(phone);

        Address addr1 = new Address("台灣省", "桃園縣中壢市", "XX路", "100001");
        Address addr2 = new Address("台灣省", "台北市中山區", "XXX路", "100002");
        Address[] addresses = {addr1, addr2};
        profile.setAddresses(addresses);

        User user = new User();
        user.setUserId(123456789L);
        user.setUsername("puma");
        user.setPassword("987654321");
        user.setAttribute("男");
        user.setProfile(profile);

        return user;
    }
}
