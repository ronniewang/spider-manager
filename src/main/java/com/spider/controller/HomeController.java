package com.spider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wsy
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String home() {

        return "index";
    }
}
