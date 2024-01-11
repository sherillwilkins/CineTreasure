package com.w83ll43.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
