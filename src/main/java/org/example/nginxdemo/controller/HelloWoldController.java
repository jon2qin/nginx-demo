package org.example.nginxdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class HelloWoldController {

    @GetMapping(value = "/hello")
    public String hello(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        return "Hello Wold! " +
                "version = v1.0.0 " +
                "remoteAddr = " + remoteAddr;
    }

}
