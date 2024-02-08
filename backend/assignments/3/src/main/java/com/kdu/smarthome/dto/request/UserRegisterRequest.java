package com.kdu.smarthome.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String username;
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
