package com.ht.test.spring.boot.mertics;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 如果要数据库自动回滚，则继承于AbstractTransactionalJUnit4SpringContextTests
 * 如果要看数据库实际测试效果，则继承于AbstractJUnit4SpringContextTests
 *
 * @author hutao <胡涛, hutao@email.com>
 * @version v1.0
 * @project coolqi-backend
 * @Description
 * @encoding UTF-8
 * @date 2016/11/28
 * @time 下午7:30
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestBase.class)
public class TestBase extends AbstractJUnit4SpringContextTests {
    @Test
    public void name() throws Exception {


    }
}
