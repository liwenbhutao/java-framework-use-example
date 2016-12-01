package com.ht.test.spring.boot.mvc.controller;

import com.ht.test.spring.boot.mvc.service.DomainService;
import com.ht.test.spring.boot.mvc.service.domain.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexView(final HttpServletRequest request, final Model model) {
        return "/index";
    }

    @RequestMapping(value = "/domain/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public long createDomain(final HttpServletRequest request, final Model model) {
        return this.domainService.createDomain();
    }

    @RequestMapping(value = "/domain/load/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Domain loadDomain(@PathVariable final long id, final HttpServletRequest request, final Model model) {
        return this.domainService.loadById(id);
    }
}
