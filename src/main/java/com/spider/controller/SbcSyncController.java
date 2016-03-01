package com.spider.controller;

import com.spider.model.JsonResult;
import com.spider.service.SbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导出赛程的Controller
 *
 * @author wsy
 */
@Controller
public class SbcSyncController {

    @Autowired
    private SbcService sbcService;

    @RequestMapping(value = "/sync", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult sync(@RequestParam String matchCode, @RequestParam(required = false) Integer type) {

        return sbcService.sync(matchCode, type);
    }

    @RequestMapping(value = "/syncOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult sync(@RequestParam String id) {

        return sbcService.syncOdds(id);
    }

}