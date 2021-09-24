package br.com.varchar.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().build();
    }
}

