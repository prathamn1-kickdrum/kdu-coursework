package com.kdu.smarthome.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRegisterRequest {
    @JsonProperty("address")
    private String address;

    @JsonProperty("house_name")
    private String houseName;

}
