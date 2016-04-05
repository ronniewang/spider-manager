package com.spider.manager.controller;

import com.spider.solr.Todo;
import com.spider.solr.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author wsy
 */
@Controller
public class HomeController {

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping("/")
    public String home() {

        todoRepository.save(new Todo("1", "desc", "title"));
        return "index";
    }

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/testPerformance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String testPerformance(HttpSession httpSession) {

        Integer times = (Integer) httpSession.getAttribute("times");
        if (times == null) {
            httpSession.setAttribute("times", 0);
        } else {
            System.out.println("times is " + times);
            httpSession.setAttribute("times", ++times);
        }
        return "{\"value\":true}";
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
