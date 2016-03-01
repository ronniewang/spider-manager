package com.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author wsy
 *
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String home() {

        return "index";
    }
}
