package com.kdu.smarthome.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginRequest {
    private String username;
    private String password;
}
