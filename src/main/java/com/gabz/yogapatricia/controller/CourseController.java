package com.gabz.yogapatricia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping("")
    public String getCourses() {

        return "course/index";
    }
}
