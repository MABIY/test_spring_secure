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
    @PostMapping("baidu")
    public String baidu(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "test_value");
        cookie.setMaxAge(3600);
        Cookie cookie1 = new Cookie("baidu", "baidu_test");
        cookie1.setMaxAge(3600);
        cookie1.setDomain("www.baidu.com");
        cookie1.setPath("/");
        response.addCookie(cookie);
        response.addCookie(cookie1);
        return "hello World";
    }
}
