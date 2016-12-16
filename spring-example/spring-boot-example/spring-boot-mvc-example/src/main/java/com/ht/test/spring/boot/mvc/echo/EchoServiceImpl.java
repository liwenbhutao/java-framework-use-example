package com.ht.test.spring.boot.mvc.echo;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 12/15/16
 * @time 7:40 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String getMessage(final String message) {
        return message + RandomStringUtils.randomAlphanumeric(10);
    }
}
