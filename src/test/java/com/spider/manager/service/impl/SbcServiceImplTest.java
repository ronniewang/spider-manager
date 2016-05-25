package com.spider.manager.service.impl;

import com.spider.db.entity.CompanyOddsEntity;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.entity.W500Entity;
import com.spider.db.repository.CompanyOddsRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.db.repository.W500Repository;
import com.spider.domain.UpdateScoreAndHalf;
import com.spider.exception.UpdateException;
import com.spider.manager.model.JsonResult;
import com.spider.manager.sbc.SbcUpdateManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.expression.AccessException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ronniewang on 16/5/22.
 *
 * @author ronnie
 */
@RunWith(MockitoJUnitRunner.class)
public class SbcServiceImplTest {

    @Mock
    private TCrawlerWin310Repository win310Repository;

    @Mock
    private W500Repository w500Repository;

    @Mock
    private CompanyOddsRepository companyOddsRepository;

    @Mock
    private SbcUpdateManager sbcUpdateManager;

    @InjectMocks
    private SbcServiceImpl sbcService = new SbcServiceImpl();

    @Test(expected = NullPointerException.class)
    public void testSyncOdds_nullArgument_shouldThrowNullPointerException() {

        sbcService.syncOdds(null);
    }

    @Test
    public void testSyncOdds_nullOdds_shouldReturnNoThisOddsResult() {

        when(companyOddsRepository.findOne(isA(Long.class))).thenAnswer(new Answer<CompanyOddsEntity>() {

            @Override
            public CompanyOddsEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        JsonResult result = sbcService.syncOdds("121");
        assertTrue(result.getDesc().contains("no this odds"));
    }

    @Test
    public void testSyncOdds_throwUpdateException_shouldReturnMqErrorResult() {

        when(companyOddsRepository.findOne(isA(Long.class))).thenAnswer(new Answer<CompanyOddsEntity>() {

            @Override
            public CompanyOddsEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                throw new UpdateException("update failed");
            }
        });
        JsonResult result = sbcService.syncOdds("121");
        assertTrue(result.getDesc().contains("mq error"));
    }


    @Test
    public void testSyncOdds_throwOtherException_shouldReturnOtherExceptionResult() {

        when(companyOddsRepository.findOne(isA(Long.class))).thenAnswer(new Answer<CompanyOddsEntity>() {

            @Override
            public CompanyOddsEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                throw new IllegalStateException("illegal state");
            }
        });
        JsonResult result = sbcService.syncOdds("121");
        assertTrue(result.getDesc().contains("illegal state"));
    }

    @Test
    public void testSyncOdds_nullWin310_shouldReturnNoThisWin310Result() {

        when(companyOddsRepository.findOne(isA(Long.class))).thenAnswer(new Answer<CompanyOddsEntity>() {

            @Override
            public CompanyOddsEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                CompanyOddsEntity entity = new CompanyOddsEntity();
                entity.setEuropeId(121);
                return entity;
            }
        });
        when(win310Repository.findByWin310EuropeId(isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        JsonResult result = sbcService.syncOdds("121");
        assertTrue(result.getDesc().contains("no this win310"));
    }

    @Test
    public void testSyncOdds_findWin310_shouldUpdateToMq() throws UpdateException {

        when(companyOddsRepository.findOne(isA(Long.class))).thenAnswer(new Answer<CompanyOddsEntity>() {

            @Override
            public CompanyOddsEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                CompanyOddsEntity entity = new CompanyOddsEntity();
                entity.setEuropeId(121);
                entity.setOddsType(SbcServiceImpl.ODDS_TYPE_HILO);
                entity.setGamingCompany("利记");
                return entity;
            }
        });
        when(win310Repository.findByWin310EuropeId(isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                TCrawlerWin310 win310 = new TCrawlerWin310();
                win310.setCompetitionNum("1001");
                return win310;
            }
        });
        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        }).when(sbcUpdateManager).update(isA(Object.class), isA(String.class));
        JsonResult result = sbcService.syncOdds("121");
        assertEquals(result.getValue(), JsonResult.SUCCESS.getValue());
    }

    @Test
    public void testSync_win310NotFound_shouldReturnErrorResult() throws Exception {

        when(win310Repository.findByUniqueId(isA(Long.class))).then(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        JsonResult result = sbcService.sync("123", 1);
        assertTrue(result.getDesc().contains("no win310"));
    }

    @Test
    public void testSync_500NotFound_shouldReturnErrorResult() throws Exception {

        when(win310Repository.findByUniqueId(isA(Long.class))).then(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new TCrawlerWin310();
            }
        });
        when(w500Repository.findByMatchCode(isA(String.class))).then(new Answer<W500Entity>() {

            @Override
            public W500Entity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        JsonResult result = sbcService.sync("123", 1);
        assertTrue(result.getDesc().contains("no w500"));
    }

    @Test
    public void testSync_queryException_shouldReturnErrorResult() throws Exception {

        when(win310Repository.findByUniqueId(isA(Long.class))).then(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new TCrawlerWin310();
            }
        });
        when(w500Repository.findByMatchCode(isA(String.class))).then(new Answer<W500Entity>() {

            @Override
            public W500Entity answer(InvocationOnMock invocationOnMock) throws Throwable {

                throw new AccessException("access error");
            }
        });
        JsonResult result = sbcService.sync("123", 1);
        assertTrue(result.getDesc().contains("access error"));
    }

    @Test
    public void testSync_updateSuccess_shouldReturnSuccessResult() throws Exception {

        when(win310Repository.findByUniqueId(isA(Long.class))).then(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                TCrawlerWin310 win310 = new TCrawlerWin310();
                win310.setWin310EuropeId("1212");
                return win310;
            }
        });
        when(w500Repository.findByMatchCode(isA(String.class))).then(new Answer<W500Entity>() {

            @Override
            public W500Entity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new W500Entity();
            }
        });
        doNothing().when(sbcUpdateManager).update(isA(Object.class), isA(String.class), isA(String.class));
        doNothing().when(sbcUpdateManager).update(isA(Object.class), isA(String.class));
        JsonResult result = sbcService.sync("201501011001", null);
        assertTrue(result.getValue().equals(JsonResult.SUCCESS.getValue()));
    }

}