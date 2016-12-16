package com.ht.test.spring.boot.mvc.controller.helper;

import lombok.experimental.UtilityClass;
import org.aeonbits.owner.ConfigCache;

/**
 * Created by huangwei on 3/2/16.
 */
@UtilityClass
public class OwnerConsts {

    /**
     * 获取用户配置
     *
     * @return
     */
    public static Config getConfig() {
        return ConfigCache.getOrCreate(Config.class);
    }

    @org.aeonbits.owner.Config.Sources("classpath:config/config.properties")
    public interface Config extends org.aeonbits.owner.Config {
        @Key("a")
        String a();
    }
}
