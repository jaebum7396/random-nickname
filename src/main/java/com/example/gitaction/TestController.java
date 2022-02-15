package com.example.gitaction;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/") public String hello()
    {

        return "Update Cashing V2!!!!!!";
    }
}


