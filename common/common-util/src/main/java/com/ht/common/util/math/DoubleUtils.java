package com.ht.common.util.math;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project coolqi-common
 * @Description 验证日期格式是否正确
 * @encoding UTF-8
 * @date 2016/11/30
 * @time 下午8:09
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
public class DoubleUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###################################");

    /**
     * 获取double数值精度
     *
     * @param value
     * @return
     */
    public static int getAccuracy(final double value) {
        final String valueStr = DECIMAL_FORMAT.format(value);
        final int index = valueStr.indexOf('.');
        if (index == -1) {
            return 0;
        }
        return valueStr.length() - index - 1;
    }

    public static BigDecimal toBigDecimal(final double value) {
        return new BigDecimal(String.valueOf(value));
    }
}
