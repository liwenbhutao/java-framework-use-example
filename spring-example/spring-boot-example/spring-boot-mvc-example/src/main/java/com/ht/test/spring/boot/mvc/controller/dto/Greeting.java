package com.ht.test.spring.boot.mvc.controller.dto;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 12/15/16
 * @time 8:05 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(final String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

}
