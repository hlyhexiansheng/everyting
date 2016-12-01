package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/11/4.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/hello")
    public ModelAndView helloPage(){
        return new ModelAndView("hello");
    }

}
