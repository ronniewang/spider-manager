package com.spider.controller;

import com.spider.db.entity.ServiceStateEntity;
import com.spider.db.entity.ServiceStateHistoryEntity;
import com.spider.db.repository.ServiceStateHistoryRepository;
import com.spider.db.repository.ServiceStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wsy
 */
@Controller
public class ServiceStateController {

    @Autowired
    private ServiceStateRepository serviceStateRepository;

    @Autowired
    private ServiceStateHistoryRepository serviceStateHistoryRepository;

    @RequestMapping(value = "/listServiceState", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ServiceStateEntity> listServiceState() {

        return serviceStateRepository.findAll();
    }

    @RequestMapping(value = "/listServiceStatePage")
    public String listServiceStatePage() {

        return "listServiceState";
    }

    @RequestMapping(value = "listHistory", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ServiceStateHistoryEntity> listHistory(String service) {

        return serviceStateHistoryRepository.findHistory(service, new PageRequest(0, 20, Sort.Direction.DESC, "startTime"));
    }
}
