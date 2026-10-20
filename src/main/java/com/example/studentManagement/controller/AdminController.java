package com.example.studentManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Admin endpoint accessed successfully");
    }
}
