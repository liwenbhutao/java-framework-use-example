package com.ht.test.spring.boot.profile.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by hutao on 16/6/2.
 * 上午12:12
 */
@Service
@Slf4j
@Profile({"profile1", "default"})
public class Profile1HelloService implements HelloService {
    @Value("${config.str:aaaaa}")
    private String str = "";

    @Override
    public void hello() {
        log.info("hello world profile1 " + str);
    }
}
