package com.kdu.smarthome.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddDeviceRequest {


    private String houseId;

    private String roomId;

    private String kickstonId;


}

