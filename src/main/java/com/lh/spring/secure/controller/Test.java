package com.lh.spring.secure.controller;

import com.lh.spring.secure.log.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Test {

    @PostMapping("test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
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
