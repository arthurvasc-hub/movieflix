package com.movieflix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CategoryController {
    @GetMapping("/category")
    public String helloWorld(){
        return "Hello World.";
    }
}
