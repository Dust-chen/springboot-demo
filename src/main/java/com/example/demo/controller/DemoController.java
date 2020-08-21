package com.example.demo.controller;

import com.example.demo.annotation.LogAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/")
    @LogAnno()
    public String demo() {
        return "hello";
    }
}
