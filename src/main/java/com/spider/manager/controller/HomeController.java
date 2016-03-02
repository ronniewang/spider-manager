package com.spider.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author wsy
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "index";
    }

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(required = false) Integer error) {

        ModelAndView mav = new ModelAndView("login");
        if (error != null) {
            if (error == 1) {
                mav.addObject("error", "Maybe invalid combination of username and password");
            }
        }
        return mav;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {

        httpSession.invalidate();
        return "redirect:/login";
    }
}
