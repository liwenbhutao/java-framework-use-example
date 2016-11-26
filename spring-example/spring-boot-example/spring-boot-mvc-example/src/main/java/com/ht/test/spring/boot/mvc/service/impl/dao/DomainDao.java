package com.ht.test.spring.boot.mvc.service.impl.dao;

import com.ht.test.spring.boot.mvc.service.domain.Domain;
import org.springframework.stereotype.Repository;

/**
 * @author hutao <hutao, hutao@email.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/11/27
 * @time 上午12:57
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Repository
public interface DomainDao {
    long insert(Domain domain);
}
