package com.spider.manager.controller;

import com.spider.manager.model.JsonResult;
import com.spider.manager.service.SbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 同步sbc的Controller
 *
 * @author ronnie
 */
@Controller
public class SbcSyncController {

    @Autowired
    private SbcService sbcService;

    /**
     * @param matchCode
     * @param type
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/sync.do", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult sync(@RequestParam String matchCode, @RequestParam(required = false) Integer type) {

        return sbcService.sync(matchCode, type);
    }

    /**
     * @param id
     * @return
     * @see SbcService
     */
    @RequestMapping(value = "/syncOdds", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult syncOdds(@RequestParam String id) {

        return sbcService.syncOdds(id);
    }

}
