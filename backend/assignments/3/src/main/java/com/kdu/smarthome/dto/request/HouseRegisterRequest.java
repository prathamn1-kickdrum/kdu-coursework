package com.kdu.smarthome.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRegisterRequest {
    @NotBlank
    @NotEmpty
    @JsonProperty("address")
    private String address;
    @NotBlank
    @NotEmpty
    @JsonProperty("house_name")
    private String houseName;

}
