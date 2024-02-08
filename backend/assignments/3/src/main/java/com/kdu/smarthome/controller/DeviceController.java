package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    @PostMapping("/register")
    public ResponseEntity<GeneralResponseDto> registerDevice(@RequestBody DeviceRegisterRequest myDto) {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponseDto> addDeviceToHouse(@RequestBody AddDeviceRequest myDto) {
        return null;
    }

}
