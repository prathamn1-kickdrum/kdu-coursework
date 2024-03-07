package com.kdu.smarthome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for testing user-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestUserController {

    /**
     * Endpoint to greet the superuser.
     *
     * @return ResponseEntity containing a greeting message for the superuser.
     */
    @GetMapping("/superuser")
    public ResponseEntity<String> saySuperUser() {
        return ResponseEntity.ok("Hi superuser");
    }

    /**
     * Endpoint to greet the user.
     *
     * @return ResponseEntity containing a greeting message for the user.
     */
    @GetMapping("/user")
    public ResponseEntity<String> sayUser() {
        return ResponseEntity.ok("Hi user");
    }
}
