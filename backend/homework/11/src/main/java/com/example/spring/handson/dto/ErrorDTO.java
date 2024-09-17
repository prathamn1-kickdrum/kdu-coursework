package com.example.spring.handson.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {
    String message;
    int statusCode;
}
