package com.example.AssessmentJava_Frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dept_emp")
public class Dept_EmpController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("dept_emp/dept_emp");
        return view;
    }
}
