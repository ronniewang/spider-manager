package com.spider.manager.service.impl;

import com.spider.db.entity.OddsModel;
import com.spider.db.entity.SportteryAllEntity;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.repository.*;
import com.spider.global.Mills;
import com.spider.manager.service.MatchOddsServcie;
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
import java.util.Arrays;
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
public class MatchOddsServiceImplTest {

    @Mock
    private TCrawlerSportteryRepository sportteryRepository;

    @Mock
    private TCrawlerWin310Repository win310Repository;

    @Mock
    private SbcLeagueService sbcLeagueService;

    @Mock
    private MatchService matchService;

    @Mock
    private SportteryAllRepository sportteryAllRepository;

    @Mock
    private CompanyOddsRepository companyOddsRepository;

    @Mock
    private CompanyOddsHistoryRepository companyOddsHistoryRepository;

    @Mock
    private OddsModelRepository oddsModelRepository;

    @InjectMocks
    private MatchOddsServcie matchOddsServcie = new MatchOddsServiceImpl();

    @Test(expected = NullPointerException.class)
    public void testListOdds_startDateIsNull_shouldThrowNullPointerException() throws Exception {

        matchOddsServcie.listOdds(null, null);
    }

    @Test
    public void testListOdds_sportteryListIsEmpty_shouldReturnEmptyList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        List<OddsModel> oddsModels = matchOddsServcie.listOdds(new Date(), new Date());
        assertTrue(oddsModels.size() == 0);
    }

    @Test
    public void testListOdds_sportteryListHasOneElement_shouldReturnOneElementList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                List<TCrawlerSporttery> sportteries = new ArrayList<>();
                TCrawlerSporttery sporttery = new TCrawlerSporttery();
                sporttery.setCompetitionNum("1001");
                sporttery.setStartDateTime(new Date(System.currentTimeMillis() + Mills.DAY));
                sportteries.add(sporttery);
                return sportteries;
            }
        });
        when(matchService.getAbsenceMatchCodes(isA(List.class))).thenAnswer(new Answer<List<String>>() {

            @Override
            public List<String> answer(InvocationOnMock invocation) throws Throwable {

                return Arrays.asList("1001");
            }
        });
        when(win310Repository.findTop1ByCompetitionNumOrderByStartDateTimeDesc(isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocation) throws Throwable {

                TCrawlerWin310 win310 = new TCrawlerWin310();
                win310.setWin310EuropeId("121221");
                win310.setStartDateTime(new Date());
                return win310;
            }
        });
        when(oddsModelRepository.findByEuropeId(isA(Integer.class))).thenAnswer(new Answer<OddsModel>() {

            @Override
            public OddsModel answer(InvocationOnMock invocation) throws Throwable {

                OddsModel oddsModel = new OddsModel();
                return oddsModel;
            }
        });
        when(sportteryAllRepository.findByMatchCode(isA(String.class))).thenAnswer(new Answer<SportteryAllEntity>() {

            @Override
            public SportteryAllEntity answer(InvocationOnMock invocation) throws Throwable {

                return new SportteryAllEntity();
            }
        });
        List<OddsModel> oddsModels = matchOddsServcie.listOdds(new Date(), new Date());
        assertTrue(oddsModels.size() == 1);
    }

    @Test
    public void testRefreshOdds_sportteryListIsEmpty_shouldNotCallWin310Repository() throws Exception {

        when(sportteryRepository.findByCompetitionNum(isA(String.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        matchOddsServcie.refreshOdds("1001");
        verify(sportteryRepository).findByCompetitionNum(isA(String.class));
        verifyNoMoreInteractions(sportteryRepository);
    }

    @Test
    public void testRefreshOdds_sportteryListIsNotEmpty_shouldCallWin310Repository() throws Exception {

        when(sportteryRepository.findByCompetitionNum(isA(String.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                ArrayList<TCrawlerSporttery> tCrawlerSportteries = new ArrayList<>();
                tCrawlerSportteries.add(new TCrawlerSporttery());
                return tCrawlerSportteries;
            }
        });
        when(win310Repository.findTop1ByCompetitionNumOrderByStartDateTimeDesc(isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        matchOddsServcie.refreshOdds("1001");
        verify(sportteryRepository).findByCompetitionNum(isA(String.class));
        verifyNoMoreInteractions(sportteryRepository);
        verify(win310Repository).findTop1ByCompetitionNumOrderByStartDateTimeDesc(isA(String.class));
        verifyNoMoreInteractions(win310Repository);
    }

    @Test(expected = NullPointerException.class)
    public void testGetExcelOddsModels_nullArgument_shouldThrowNullPointerException() throws Exception {

        matchOddsServcie.getExcelOddsModels(null, null, null);
    }

    @Test
    public void testGetExcelOddsModels_win310ListIsEmpty_shouldNotCallCompanyOddsHistoryRepository() throws Exception {

        when(win310Repository.findByMatchsAndUpdateTimeBetween(isA(String.class), isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerWin310>>() {

            @Override
            public List<TCrawlerWin310> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        matchOddsServcie.getExcelOddsModels(new Date(), new Date(), "csl");
        verify(win310Repository).findByMatchsAndUpdateTimeBetween(isA(String.class), isA(Date.class), isA(Date.class));
        verifyNoMoreInteractions(win310Repository);
    }
}