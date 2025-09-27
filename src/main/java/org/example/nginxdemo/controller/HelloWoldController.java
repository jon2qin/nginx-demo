package org.example.nginxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/test")
public class HelloWoldController {

    @GetMapping(value = "/hello")
    public String hello() {
        Random random = new Random();
        int nextInt = random.nextInt(101);
        HashMap<Object, Object> map = new HashMap<>(nextInt);
        for (int i = 0; i < nextInt; i++) {
            map.put(i, new Object());
        }
        return "Hello Wold! size = " + map.size();
    }

    @GetMapping(value = "/good")
    public String test() {
        return "soga!";
    }

}
