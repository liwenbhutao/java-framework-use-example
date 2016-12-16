package com.ht.common.util.constant;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

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
public class RegexPatternConstant {

    public static final Pattern DIGIT_PATTERN = Pattern.compile("^[0-9]*$");
    public static final Pattern DIGIT_WITH_ALPHABET_PATTERN = Pattern.compile("^[0-9a-zA-Z]*$");
    public static final Pattern ALPHABET_PATTERN = Pattern.compile("^[a-zA-Z]*$");
    public static final Pattern CHINESE_PATTERN = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3])*$");

    public static final Pattern URL_PATTERN = Pattern.compile("^((https|http)?:\\/\\/)[^\\s]+");
    public static final Pattern CHINA_PHONE = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
    public static final Pattern CHINA_IDCARD = Pattern.compile("^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");


}
