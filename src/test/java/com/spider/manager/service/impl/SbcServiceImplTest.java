package com.spider.manager.service.impl;

import com.spider.db.entity.TCrawlerWin310;
import com.spider.db.entity.W500Entity;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.db.repository.W500Repository;
import com.spider.manager.model.JsonResult;
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
public class SbcServiceImplTest {

    @Mock
    private TCrawlerWin310Repository win310Repository;

    @Mock
    private W500Repository w500Repository;

    @InjectMocks
    private SbcServiceImpl sbcService = new SbcServiceImpl();

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
        verify(win310Repository).findByUniqueId(isA(Long.class));
        verifyNoMoreInteractions(win310Repository);
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
        verify(win310Repository).findByUniqueId(isA(Long.class));
        verifyNoMoreInteractions(win310Repository);
        verify(w500Repository).findByMatchCode(isA(String.class));
        verifyNoMoreInteractions(win310Repository);
    }

}