package com.ht.common.spring.util.database;

import lombok.experimental.UtilityClass;

/**
 * Created by LiWei on 2016/8/2.
 */
@UtilityClass
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String getDynamicDataSourceName() {
        return holder.get();
    }

    public static void setDynamicDataSourceName(final String name) {
        holder.set(name);
    }

    public static void clearDynamicDataSourceName() {
        holder.remove();
    }
}
