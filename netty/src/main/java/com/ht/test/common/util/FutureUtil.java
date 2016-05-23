package com.ht.test.common.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.Future;

/**
 * Created by hutao on 16/5/18.
 * 下午5:53
 */
@UtilityClass
public class FutureUtil {
    public static boolean isFinish(final Future future) {
        return future.isDone() || future.isCancelled();
    }

    public static boolean isNotFinish(final Future future) {
        return !isFinish(future);
    }
}
