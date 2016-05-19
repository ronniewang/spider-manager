package com.spider.db.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.spider.db.entity.W500Entity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by wsy on 2015/11/30.
 *
 * @author ronnie
 */
@DatabaseSetup(value = {"classpath:w500.xml"})
public class W500RepositoryTest extends RepositoryTestSupport {

    @Autowired
    private W500Repository w500Repository;

    @Test
    public void findByMatchCode_NoResultFound_ShouldReturnNull() {

        W500Entity w500Entity = w500Repository.findByMatchCode("362227");
        assertTrue("should not find a entity", w500Entity == null);
    }

    @Test
    public void findByMatchCode_OneResultFound_ShouldReturnOneObject() {

        W500Entity w500Entity = w500Repository.findByMatchCode("201603162007");
        assertTrue("should find an entity", w500Entity != null);
        assertTrue("odds not correct", w500Entity.getHadH() == 4.3);
    }
}