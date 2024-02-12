package com.kdu.smarthome.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    @NotBlank
    @NotEmpty
    private String firstName;
    @NotBlank
    @NotEmpty
    private String lastName;
    @NotBlank
    @NotEmpty
    private String emailId;
    @NotBlank
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String username;
    @NotBlank
    @NotEmpty
    private String name;

    @Override
    public String toString() {
        return "{" +
                ",name='" + name + '\'' +
                ",username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + emailId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
