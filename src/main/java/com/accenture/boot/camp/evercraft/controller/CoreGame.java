package com.accenture.boot.camp.evercraft.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CoreGame {
    public CoreGame(){};

    @GetMapping("/")

    public String getHelloWorld() {
        return "Hello, World!";
    };

    @GetMapping("/person")
    public String getHelloName(@RequestParam(required = false) String name){
        if(name == null || name.isBlank()) {
            return "Hello, Person!";
        } else {
            return ("Hello, " + name + "!");
        }
    }
}