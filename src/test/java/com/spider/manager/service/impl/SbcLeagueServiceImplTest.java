package com.spider.manager.service.impl;

import com.spider.db.entity.SbcLeague;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.repository.SbcLeagueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ronniewang on 16/5/22.
 */
@RunWith(MockitoJUnitRunner.class)
public class SbcLeagueServiceImplTest {

    @Mock
    private SbcLeagueRepository sbcLeagueRepository;

    @InjectMocks
    private SbcLeagueServiceImpl sbcLeagueService = new SbcLeagueServiceImpl();

    @Test
    public void testGetLeagueName_leagueExists_shouldReturnLeagueName() throws Exception {

        when(sbcLeagueRepository.findBySportteryName(isA(String.class))).then(new Answer<SbcLeague>() {
            @Override
            public SbcLeague answer(InvocationOnMock invocationOnMock) throws Throwable {

                SbcLeague sl = new SbcLeague();
                sl.setLeagueNameAbbr("ABBR");
                return sl;
            }
        });
        TCrawlerSporttery sporttery = new TCrawlerSporttery();
        sporttery.setMatchs("csl");
        String result = sbcLeagueService.getLeagueName(sporttery);
        assertTrue(result.equals("ABBR"));
        verify(sbcLeagueRepository).findBySportteryName(isA(String.class));
        verifyNoMoreInteractions(sbcLeagueRepository);
    }

    @Test
    public void testGetLeagueName_leagueNotExists_shouldReturnSportteryMatchs() throws Exception {

        when(sbcLeagueRepository.findBySportteryName(isA(String.class))).then(new Answer<SbcLeague>() {
            @Override
            public SbcLeague answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        });
        TCrawlerSporttery sporttery = new TCrawlerSporttery();
        sporttery.setMatchs("csl");
        String result = sbcLeagueService.getLeagueName(sporttery);
        assertTrue(result.equals("csl"));
        verify(sbcLeagueRepository).findBySportteryName(isA(String.class));
        verifyNoMoreInteractions(sbcLeagueRepository);
    }
}