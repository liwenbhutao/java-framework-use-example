package com.ht.common.util.constant;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project coolqi-common
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午4:25
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@UtilityClass
public final class BigDecimalConstant {
    /**
     * 结果精度
     */
    public static final int DEFAULT_RESULT_SCALA = 2;
    /**
     * 除法结果精度
     */
    public static final int DEFAULT_DIVIDE_SCALA = 10;
    /**
     * 默认四舍五入的方式
     */
    public static final int DEFAULT_ROUND_MODE = BigDecimal.ROUND_HALF_EVEN;
    /**
     *
     */
    public static final BigDecimal YEAR_DAY_COUNT = new BigDecimal("360");
    /**
     *
     */
    public static final BigDecimal YEAR_MONTH_COUNT = new BigDecimal("12");
    /**
     *
     */
    public static final BigDecimal MONTH_DAY_COUNT = new BigDecimal("30");
    /**
     *
     */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
}
