package com.ht.test.spring.boot.mvc.service.impl;

import com.google.common.base.Preconditions;
import com.ht.test.spring.boot.mvc.service.DomainService;
import com.ht.test.spring.boot.mvc.service.domain.Domain;
import com.ht.test.spring.boot.mvc.service.impl.dao.DomainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hutao <hutao, hutao@email.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/11/27
 * @time 上午12:56
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class DomainServiceImpl implements DomainService {
    @Autowired
    private DomainDao domainDao;

    @Override
    public long createDomain() {
        final Domain domain = new Domain();
        domainDao.insert(domain);
        return domain.getId();
    }

    @Override
    public Domain loadById(long id) {
        final Domain domain = domainDao.loadById(id);
        Preconditions.checkNotNull(domain);
        return domain;
    }
}
