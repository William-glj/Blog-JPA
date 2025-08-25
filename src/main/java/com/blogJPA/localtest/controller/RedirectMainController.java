package com.blogJPA.localtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectMainController {
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/api";
    }

}