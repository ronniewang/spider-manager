package com.spider.manager.controller;

import com.spider.db.repository.ServiceStateHistoryRepository;
import com.spider.db.repository.ServiceStateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ronniewang on 16/5/22.
 *
 * @author ronnie
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceStateControllerTest {

    @Mock
    private ServiceStateRepository serviceStateRepository = Mockito.mock(ServiceStateRepository.class);

    @Mock
    private ServiceStateHistoryRepository serviceStateHistoryRepository = Mockito.mock(ServiceStateHistoryRepository.class);

    @InjectMocks
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