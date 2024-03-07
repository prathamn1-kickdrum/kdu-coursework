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
public class InventoryRequest {
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
    @NotBlank
    @NotEmpty
    @JsonProperty("manufacture_date_time")
    private String manufactureDateTime;
    @NotBlank
    @NotEmpty
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;

}
