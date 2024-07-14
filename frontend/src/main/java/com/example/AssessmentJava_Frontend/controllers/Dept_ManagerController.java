package com.example.AssessmentJava_Frontend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("dept_manager")
public class Dept_ManagerController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("dept_manager/dept_manager");
        return view;
    }
}
