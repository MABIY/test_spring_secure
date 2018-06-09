package com.lh.spring.secure.controller;

import com.lh.spring.secure.log.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @PostMapping("test")
    @Log("log test")
    public String test() {
        return "Hello World";
    }
}
