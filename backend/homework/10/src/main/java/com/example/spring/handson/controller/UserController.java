package com.example.spring.handson.controller;

import com.example.spring.handson.exception.custom.NoUserFoundException;
import com.example.spring.handson.dto.UserDto;
import com.example.spring.handson.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("public/user/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/public/search/user")
    public ResponseEntity<UserDto> getUserByName(@RequestParam String name) throws NoUserFoundException {
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    @PostMapping("/add/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }



}
