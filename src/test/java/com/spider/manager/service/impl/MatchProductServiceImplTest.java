package com.spider.manager.service.impl;

import com.spider.db.entity.SportteryAllEntity;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.repository.SportteryAllRepository;
import com.spider.db.repository.TCrawlerSportteryRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.manager.model.ProductModel;
import com.spider.manager.service.MatchProductService;
import com.spider.manager.service.MatchService;
import com.spider.manager.service.SbcLeagueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ronnie on 2016/5/25.
 *
 * @author ronnie
 */
@RunWith(MockitoJUnitRunner.class)
public class MatchProductServiceImplTest {

    @Mock
    private TCrawlerSportteryRepository sportteryRepository;

    @Mock
    private TCrawlerWin310Repository win310Repository;

    @Mock
    private SportteryAllRepository sportteryAllRepository;

    @Mock
    private SbcLeagueService sbcLeagueService;

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchProductService matchProductService = new MatchProductServiceImpl();

    @Test(expected = NullPointerException.class)
    public void testListMatchProduct_nullArgument_shouldThrowNullPointerException() throws Exception {

        matchProductService.listMatchProduct(null, null);
    }

    @Test
    public void testListMatchProduct_sportteryListIsEmpty_shouldReturnEmptyList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        List<ProductModel> productModels = matchProductService.listMatchProduct(new Date(), new Date());
        assertTrue(productModels.size() == 0);
    }

    @Test
    public void testListMatchProduct_sportteryListHasOneElement_shouldReturnOneElementList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                ArrayList<TCrawlerSporttery> tCrawlerSportteries = new ArrayList<>();
                TCrawlerSporttery sporttery = new TCrawlerSporttery();
                sporttery.setStartDateTime(new Date());
                sporttery.setCompetitionNum("1001");
                sporttery.setId(1L);
                tCrawlerSportteries.add(sporttery);
                return tCrawlerSportteries;
            }
        });
        when(matchService.getAbsenceMatchCodes(isA(List.class))).thenAnswer(new Answer<List<String>>() {

            @Override
            public List<String> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        when(win310Repository.findByStartDateTimeAndCompetitionNum(isA(Date.class), isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new TCrawlerWin310();
            }
        });
        when(sportteryAllRepository.findByMatchCode(isA(String.class))).thenAnswer(new Answer<SportteryAllEntity>() {

            @Override
            public SportteryAllEntity answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new SportteryAllEntity();
            }
        });
        List<ProductModel> productModels = matchProductService.listMatchProduct(new Date(), new Date());
        assertTrue(productModels.size() == 1);
    }
}