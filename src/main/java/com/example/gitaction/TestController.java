package com.example.gitaction;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${custom.myname}")
    private String name;

    @GetMapping("/") public String hello()
    {
        return "Hello"+name;
    }
}


