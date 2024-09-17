package com.example.spring.handson.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantDTO {
    private String name;
    private String createdBy;
    private String updatedBy;
}
