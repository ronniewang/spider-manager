package com.spider.db.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.spider.config.DataBaseTestConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created on 2016/3/22-11:07.
 * <p>
 * 参考: http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-integration-testing/
 *
 * @author ronnie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataBaseTestConfig.class})
@TestExecutionListeners(value = {
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DirtiesContext
@Ignore
public class RepositoryTestSupport {
}
