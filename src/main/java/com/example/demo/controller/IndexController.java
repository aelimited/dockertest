package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class IndexController {

    @Value("${index.string}")
    String index;

    //GET http://localhost:8080/api/index
    @GetMapping("index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body(index);
    }

    @GetMapping("welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok().body("welcome");
    }


    @GetMapping("why")
    public ResponseEntity<String> why() {
        return ResponseEntity.ok().body("why");
    }
    @GetMapping("hh")
    public ResponseEntity<String> hh() {
        return ResponseEntity.ok().body("hh");
    }

}