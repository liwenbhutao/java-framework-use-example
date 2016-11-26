package com.ht.test.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2016/11/21.
 *
 * @author hutao
 * @version 1.0
 */
@Controller
public class IndexController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    public String index() {
        return "redirect:/index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String indexView(final HttpServletRequest request, final Model model) {
        return "/index";
    }
}
