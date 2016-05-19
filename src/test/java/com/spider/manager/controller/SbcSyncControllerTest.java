package com.spider.manager.controller;

import com.spider.config.BasicTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ronniewang on 16/5/22.
 */
public class SbcSyncControllerTest extends BasicTest {

    private MockMvc mockMvc;

    @Autowired
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