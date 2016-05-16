package com.spider.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ronnie
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

}
