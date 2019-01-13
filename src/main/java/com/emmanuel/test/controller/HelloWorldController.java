package com.emmanuel.test.controller;

import com.emmanuel.test.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping({"/", "/hello"})
    public String helloWorld() {
        return "Hello-World";
    }


}
