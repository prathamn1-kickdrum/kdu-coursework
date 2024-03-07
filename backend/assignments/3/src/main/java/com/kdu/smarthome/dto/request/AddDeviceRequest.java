package com.kdu.smarthome.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceRequest {


    @NotBlank
    @NotEmpty
    private String houseId;
    @NotBlank
    @NotEmpty
    private String roomId;
    @NotBlank
    @NotEmpty
    private String kickstonId;


}

