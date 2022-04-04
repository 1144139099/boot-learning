package com.hlh.bootfilter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(HttpServletRequest request, HttpSession session){
        request.setAttribute("a", "a");
        request.setAttribute("a", "b");
        request.getAttribute("a");
        request.removeAttribute("a");

        session.setAttribute("b", "b");
        session.getAttribute("a");
        session.invalidate();

        return "hello world---";
    }
}
