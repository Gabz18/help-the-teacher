package com.gabz.yogapatricia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex() {

        return "home/index";
    }

    @GetMapping("/login")
    public String getLogin() {

        return "home/login";
    }
}
