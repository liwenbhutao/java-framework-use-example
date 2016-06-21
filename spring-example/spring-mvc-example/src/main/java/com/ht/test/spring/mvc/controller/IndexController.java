package com.ht.test.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 16/6/21.
 *
 * @author hutao
 * @version 1.0
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/", "", "/index"})
    public String index() {
        return "/index";
    }
}
