package com.kdu.smarthome.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserToHouseRequest {
    private String username;
}
