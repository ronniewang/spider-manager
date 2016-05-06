package com.spider.manager.controller;

import com.spider.manager.model.User;
import com.spider.manager.service.GitHubLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Callable;

/**
 * @author ronnie
 */
@Controller
public class RobotController {

    @Autowired
    private GitHubLookupService gitHubLookupService;

    @RequestMapping(value = "/async", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Callable<User> async() {

        return new Callable<User>() {

            @Override
            public User call() throws Exception {

                return gitHubLookupService.findUser("CloudFoundry");
            }
        };
    }
}
