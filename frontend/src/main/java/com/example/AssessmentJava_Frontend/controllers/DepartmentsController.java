package com.example.AssessmentJava_Frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("departments")
public class DepartmentsController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("departments/departments");
        return view;
    }
}
