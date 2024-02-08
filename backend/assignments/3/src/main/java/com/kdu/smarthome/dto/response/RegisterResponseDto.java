package com.kdu.smarthome.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDto {
    private String msg;
    private String token;
    private String refreshToken;
}
