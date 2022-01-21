package ru.gb.sbgb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping
    public String helloMessage() {
        return "Hello Spring Boot";
    }

    @GetMapping("/hello")
    public String helloMessageJsp(Model model) {
        model.addAttribute("msg", "Hello Spring Boot");
        return "hello";
    }
}
