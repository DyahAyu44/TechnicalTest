package com.example.AssessmentJava_Frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("salaries")
public class SalariesController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("salaries/salaries");
        return view;
    }
}
