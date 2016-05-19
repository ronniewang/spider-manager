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
public class MatchControllerTest extends BasicTest{

    @Autowired
    private MatchController matchController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
    }

    @Test
    public void testListMatch() throws Exception {

        mockMvc.perform(get("/listMatch").param("startDate", "2000/01/01").param("endDate", "2000/01/02"))
                .andExpect(status().isOk());
    }

}