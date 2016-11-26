package com.ht.test.spring.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2016/11/21.
 *
 * @author hutao
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("a")
    public String a() {
        return "a";
    }
}
