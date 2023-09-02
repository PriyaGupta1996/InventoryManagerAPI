package com.craftdemo.inventorymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController

public class HealthCheck {
    @GetMapping("/ping")
    public HashMap<String,String> ping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("response","pong");
        return map;
    }
}
