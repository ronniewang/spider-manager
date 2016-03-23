package com.spider.manager.controller;

import com.alibaba.fastjson.JSON;
import com.spider.db.entity.SbcTeamEntity;
import com.spider.manager.model.JsonResult;
import com.spider.db.repository.SbcTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 修改队名匹配表
 *
 * @author wsy
 */
@Controller
public class MatchTeamController {

    @Autowired
    private SbcTeamRepository sbcTeamRepository;

    @RequestMapping(value = "/listMatchTeam", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<SbcTeamEntity> listMatchTeam() {

        return sbcTeamRepository.findAll();
    }

    @RequestMapping(value = "/listMatchTeamPage")
    public String listMatchTeamPage() {

        return "listMatchTeam";
    }

    @RequestMapping(value = "/modifyTeam", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public JsonResult modifyLeague(String team) {

        try {
            SbcTeamEntity sbcLeague = JSON.parseObject(team, SbcTeamEntity.class);
            sbcTeamRepository.save(sbcLeague);
            return new JsonResult(0, "success");
        } catch (Exception e) {
            return new JsonResult(500, "failed");
        }
    }

}
