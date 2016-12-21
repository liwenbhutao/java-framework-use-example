package com.ht.test.spring.boot.mvc.service;

import com.ht.test.spring.boot.mvc.service.domain.Domain;

/**
 * Created on 2016/11/11.
 *
 * @author hutao
 * @version 1.0
 */
public interface DomainService {
    long createDomain();

    Domain loadById(final long id);

    Domain loadById2(final long id);
}
