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
public class ServiceStateControllerTest extends BasicTest{

    @Autowired
    private ServiceStateController serviceStateController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(serviceStateController).build();
    }

    @Test
    public void testListServiceState() throws Exception {

        mockMvc.perform(get("/listServiceState"))
                .andExpect(status().isOk());
    }

    @Test
    public void testListHistory() throws Exception {

        mockMvc.perform(get("/listHistory").param("service", "lj-robot"))
                .andExpect(status().isOk());
    }
}