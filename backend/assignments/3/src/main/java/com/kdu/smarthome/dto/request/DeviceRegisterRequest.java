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
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRegisterRequest {
    @NotBlank
    @NotEmpty
    @JsonProperty("kickston_id")
    private String kickstonId;
    @NotBlank
    @NotEmpty
    @JsonProperty("device_username")
    private String deviceUsername;
    @NotBlank
    @NotEmpty
    @JsonProperty("device_password")
    private String devicePassword;

}
