package com.ht.test.spring.boot.mvc.controller;

import com.ht.test.spring.boot.mvc.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2016/11/21.
 *
 * @author hutao
 * @version 1.0
 */
@Controller
public class IndexController {
    @Autowired
    private DomainService domainService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""})
    public String index() {
        return "/index";
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

    @RequestMapping(value = "/domain/create")
    @ResponseBody
    public long createDomain(final HttpServletRequest request, final Model model) {
        return domainService.createDomain();
    }
}
