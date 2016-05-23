package com.spider.manager.controller;

import com.spider.manager.service.SbcService;
import com.spider.manager.service.impl.SbcServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ronniewang on 16/5/22.
 */
@RunWith(MockitoJUnitRunner.class)
public class SbcSyncControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SbcService sbcService = new SbcServiceImpl();

    @InjectMocks
    private SbcSyncController sbcSyncController;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(sbcSyncController).build();
    }

    @Test
    public void testSync() throws Exception {

        mockMvc.perform(get("/sync.do").param("matchCode", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSyncOdds() throws Exception {

        mockMvc.perform(get("/syncOdds").param("id", "1"))
                .andExpect(status().isOk());
    }
}