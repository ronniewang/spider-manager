package com.spider.manager.service.impl;

import com.spider.db.entity.NowgoalKeyEvent;
import com.spider.db.entity.NowgoalMatchPlayersEntity;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.repository.NowgoalKeyEventRepository;
import com.spider.db.repository.NowgoalMatchPlayersRepository;
import com.spider.db.repository.TCrawlerSportteryRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.manager.model.MatchModel;
import com.spider.manager.model.MatchPlayerInfoModel;
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
public class MatchServiceImplTest {

    @Mock
    private TCrawlerSportteryRepository sportteryRepository;

    @Mock
    private TCrawlerWin310Repository win310Repository;

    @Mock
    private SbcLeagueService sbcLeagueService;

    @Mock
    private NowgoalMatchPlayersRepository playersRepository;

    @Mock
    private NowgoalKeyEventRepository keyEventRepository;

    @InjectMocks
    private MatchService matchService = new MatchServiceImpl();

    @Test(expected = NullPointerException.class)
    public void testListMatch_nullArgument_shouldThrowNullPointerException() throws Exception {

        matchService.listMatch(null, null);
    }

    @Test
    public void testListMatch_sportteryListIsEmpty_shouldReturnEmptyList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        List<MatchModel> matchModels = matchService.listMatch(new Date(), new Date());
        assertTrue(matchModels.size() == 0);
    }

    @Test
    public void testListMatch_sportteryListHasOneElement_shouldReturnOneElementList() throws Exception {

        when(sportteryRepository.findByStartDateTimeBetween(isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerSporttery>>() {

            @Override
            public List<TCrawlerSporttery> answer(InvocationOnMock invocationOnMock) throws Throwable {

                ArrayList<TCrawlerSporttery> tCrawlerSportteries = new ArrayList<>();
                TCrawlerSporttery sporttery = new TCrawlerSporttery();
                sporttery.setCompetitionNum("1001");
                tCrawlerSportteries.add(sporttery);
                return tCrawlerSportteries;
            }
        });
        when(win310Repository.findByStartDateTimeAndCompetitionNum(isA(Date.class), isA(String.class))).thenAnswer(new Answer<TCrawlerWin310>() {

            @Override
            public TCrawlerWin310 answer(InvocationOnMock invocation) throws Throwable {

                TCrawlerWin310 win310 = new TCrawlerWin310();
                win310.setCompetitionNum("1001");
                return win310;
            }
        });
        when(sbcLeagueService.getLeagueName(isA(TCrawlerSporttery.class))).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {

                return "csl";
            }
        });
        List<MatchModel> matchModels = matchService.listMatch(new Date(), new Date());
        assertTrue(matchModels.size() == 1);
    }

    @Test
    public void testListMatchByLeague_win310ListIsEmpty_shouldReturnEmptyList() throws Exception {

        when(win310Repository.findByMatchsAndUpdateTimeBetween(isA(String.class), isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerWin310>>() {

            @Override
            public List<TCrawlerWin310> answer(InvocationOnMock invocationOnMock) throws Throwable {

                return new ArrayList<>();
            }
        });
        List<MatchPlayerInfoModel> playerInfoModels = matchService.listMatchByLeague(new Date(), new Date(), "csl");
        assertTrue(playerInfoModels.size() == 0);
    }


    @Test
    public void testListMatchByLeague_win310ListHasOneElementAndHomeTeamAndIsFirst_shouldReturnOneElementList() throws Exception {

        buildByLeaguePrepare();
        when(playersRepository.findByMatchId(isA(Long.class))).thenAnswer(new Answer<List<NowgoalMatchPlayersEntity>>() {

            @Override
            public List<NowgoalMatchPlayersEntity> answer(InvocationOnMock invocation) throws Throwable {

                List<NowgoalMatchPlayersEntity> playersEntities = new ArrayList<>();
                NowgoalMatchPlayersEntity entity = new NowgoalMatchPlayersEntity();
                entity.setPlayer("john");
                entity.setTeam(1);
                entity.setIsFirst(true);
                playersEntities.add(entity);
                return playersEntities;
            }
        });
        List<MatchPlayerInfoModel> playerInfoModels = matchService.listMatchByLeague(new Date(), new Date(), "csl");
        assertTrue(playerInfoModels.size() == 1);
    }

    @Test
    public void testListMatchByLeague_win310ListHasOneElementAndHomeTeamAndNotFirst_shouldReturnOneElementList() throws Exception {

        buildByLeaguePrepare();
        when(playersRepository.findByMatchId(isA(Long.class))).thenAnswer(new Answer<List<NowgoalMatchPlayersEntity>>() {

            @Override
            public List<NowgoalMatchPlayersEntity> answer(InvocationOnMock invocation) throws Throwable {

                List<NowgoalMatchPlayersEntity> playersEntities = new ArrayList<>();
                NowgoalMatchPlayersEntity entity = new NowgoalMatchPlayersEntity();
                entity.setPlayer("john");
                entity.setTeam(1);
                entity.setIsFirst(false);
                playersEntities.add(entity);
                return playersEntities;
            }
        });
        List<MatchPlayerInfoModel> playerInfoModels = matchService.listMatchByLeague(new Date(), new Date(), "csl");
        assertTrue(playerInfoModels.size() == 1);
    }

    private void buildByLeaguePrepare() {

        when(win310Repository.findByMatchsAndUpdateTimeBetween(isA(String.class), isA(Date.class), isA(Date.class))).thenAnswer(new Answer<List<TCrawlerWin310>>() {

            @Override
            public List<TCrawlerWin310> answer(InvocationOnMock invocationOnMock) throws Throwable {

                ArrayList<TCrawlerWin310> tCrawlerWin310s = new ArrayList<>();
                TCrawlerWin310 win310 = new TCrawlerWin310();
                win310.setWin310EuropeId("121");
                tCrawlerWin310s.add(win310);
                return tCrawlerWin310s;
            }
        });
        when(keyEventRepository.findByMatchId(isA(Long.class))).thenAnswer(new Answer<List<NowgoalKeyEvent>>() {

            @Override
            public List<NowgoalKeyEvent> answer(InvocationOnMock invocation) throws Throwable {

                List<NowgoalKeyEvent> keyEvents = new ArrayList<>();
                NowgoalKeyEvent keyEvent = new NowgoalKeyEvent();
                keyEvent.setRelativePlayer("john");
                keyEvent.setEventDesc("Yellow Card");
                keyEvents.add(keyEvent);
                return keyEvents;
            }
        });
    }

    @Test
    public void testListMatchByLeague_win310ListHasOneElementAndAwayTeamIsFirst_shouldReturnOneElementList() throws Exception {

        buildByLeaguePrepare();
        when(playersRepository.findByMatchId(isA(Long.class))).thenAnswer(new Answer<List<NowgoalMatchPlayersEntity>>() {

            @Override
            public List<NowgoalMatchPlayersEntity> answer(InvocationOnMock invocation) throws Throwable {

                List<NowgoalMatchPlayersEntity> playersEntities = new ArrayList<>();
                NowgoalMatchPlayersEntity entity = new NowgoalMatchPlayersEntity();
                entity.setPlayer("john");
                entity.setTeam(2);
                entity.setIsFirst(true);
                playersEntities.add(entity);
                return playersEntities;
            }
        });
        List<MatchPlayerInfoModel> playerInfoModels = matchService.listMatchByLeague(new Date(), new Date(), "csl");
        assertTrue(playerInfoModels.size() == 1);
    }

    @Test
    public void testListMatchByLeague_win310ListHasOneElementAndAwayTeamNotFirst_shouldReturnOneElementList() throws Exception {

        buildByLeaguePrepare();
        when(playersRepository.findByMatchId(isA(Long.class))).thenAnswer(new Answer<List<NowgoalMatchPlayersEntity>>() {

            @Override
            public List<NowgoalMatchPlayersEntity> answer(InvocationOnMock invocation) throws Throwable {

                List<NowgoalMatchPlayersEntity> playersEntities = new ArrayList<>();
                NowgoalMatchPlayersEntity entity = new NowgoalMatchPlayersEntity();
                entity.setPlayer("john");
                entity.setTeam(2);
                entity.setIsFirst(false);
                playersEntities.add(entity);
                return playersEntities;
            }
        });
        List<MatchPlayerInfoModel> playerInfoModels = matchService.listMatchByLeague(new Date(), new Date(), "csl");
        assertTrue(playerInfoModels.size() == 1);
    }
}