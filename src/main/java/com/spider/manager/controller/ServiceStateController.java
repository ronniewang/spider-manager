package com.spider.manager.controller;

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
 * 关于爬虫运行情况的Controller
 * 此Controller对应爬虫运行信息页面
 *
 * @author ronnie
 */
@Controller
public class ServiceStateController {

    @Autowired
    private ServiceStateRepository serviceStateRepository;

    @Autowired
    private ServiceStateHistoryRepository serviceStateHistoryRepository;

    /**
     * 所有爬虫的最新一次运行情况
     *
     * @return
     */
    @RequestMapping(value = "/listServiceState", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ServiceStateEntity> listServiceState() {

        return serviceStateRepository.findAll();
    }

    /**
     * 爬虫运行情况页面
     *
     * @return
     */
    @RequestMapping(value = "/listServiceStatePage")
    public String listServiceStatePage() {

        return "listServiceState";
    }

    /**
     * 列出最近爬虫20次的运行情况
     *
     * @param service 爬虫名称
     * @return
     */
    @RequestMapping(value = "/listHistory", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ServiceStateHistoryEntity> listHistory(String service) {

        return serviceStateHistoryRepository.findHistory(service, new PageRequest(0, 20, Sort.Direction.DESC, "startTime"));
    }
}
