package com.spider.controller;

import com.alibaba.fastjson.JSON;
import com.spider.db.entity.SbcLeague;
import com.spider.model.JsonResult;
import com.spider.db.repository.SbcLeagueRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 导出赛程的Controller
 *
 * @author wsy
 */
@Controller
public class MatchLeagueController {

    @Autowired
    private SbcLeagueRepository sbcLeagueRepository;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @RequestMapping(value = "/listMatchLeague", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<SbcLeague> listMatchLeague() {

        return sbcLeagueRepository.findAll();
    }

    @RequestMapping(value = "/listMatchLeaguePage")
    public String listMatchLeaguePage() {

        return "listMatchLeague";
    }

    @RequestMapping(value = "/list310Leagues", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<String> list310Leagues() {

        return win310Repository.findMatchsDistinct();
    }

    @RequestMapping(value = "/modifyLeague", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult modifyLeague(String league) {

        try {
            SbcLeague sbcLeague = JSON.parseObject(league, SbcLeague.class);
            sbcLeagueRepository.save(sbcLeague);
            return new JsonResult(0, "success");
        } catch (Exception e) {
            return new JsonResult(500, "failed");
        }
    }

}
