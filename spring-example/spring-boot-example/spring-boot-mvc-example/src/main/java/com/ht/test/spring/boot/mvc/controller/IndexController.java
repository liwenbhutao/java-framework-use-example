package com.ht.test.spring.boot.mvc.controller;

import com.ht.test.spring.boot.mvc.controller.dto.CreateDomainParamsDto;
import com.ht.test.spring.boot.mvc.controller.helper.OwnerConsts;
import com.ht.test.spring.boot.mvc.service.DomainService;
import com.ht.test.spring.boot.mvc.service.domain.Domain;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created on 2016/11/21.
 *
 * @author hutao
 * @version 1.0
 */
@Controller
public class IndexController {
    //    @Autowired
    private DomainService domainService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String index() {
        this.domainService.loadById(1);
        this.domainService.loadById2(1);
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

    @ApiOperation(value = "")
    @RequestMapping(value = "/domain/create", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public long createDomain(@Valid final CreateDomainParamsDto createDomainParamsDto,
                             @RequestParam(name = "id") final long io) {
        return this.domainService.createDomain();
    }

    @RequestMapping(value = "/domain/load/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Domain loadDomain(@PathVariable final long id, final HttpServletRequest request, final Model model) {
        return this.domainService.loadById(id);
    }

    @PostConstruct
    void a() {

        final String a = OwnerConsts.getConfig().a();
        System.out.println(a);
    }
}
