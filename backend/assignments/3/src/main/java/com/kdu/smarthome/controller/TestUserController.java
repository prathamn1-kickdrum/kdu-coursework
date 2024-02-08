package com.kdu.smarthome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestUserController {
    @GetMapping("/superuser")
    public ResponseEntity<String> saySuperUser() {
        return ResponseEntity.ok("Hi superuser");
    }

    @GetMapping("/user")
    public ResponseEntity<String> sayUser() {
        return ResponseEntity.ok("Hi user");
    }
}
