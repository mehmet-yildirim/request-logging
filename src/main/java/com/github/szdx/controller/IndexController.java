package com.github.szdx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @PostMapping("/index")
    public ResponseEntity<TestData> test(@RequestBody TestData testData) {
        return ResponseEntity.ok(testData);
    }

}
