package com.example.spring.handson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String password;
    private String email;
    private String role;
}
