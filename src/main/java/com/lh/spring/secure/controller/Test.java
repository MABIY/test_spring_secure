package com.lh.spring.secure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Test {

    @PostMapping("baidu")
    public String baiduTest(HttpServletRequest request, HttpServletResponse response) {
        return "baidu World";
    }

    @PostMapping("lh")
    public String lhTest(HttpServletRequest request, HttpServletResponse response) {
        return "lh World";
    }
}
