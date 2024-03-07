package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.service.DeviceService;
import com.kdu.smarthome.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for handling device-related endpoints.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * Registers a new device.
     *
     * @param myDto DeviceRegisterRequest instance containing device registration data.
     * @return ResponseEntity containing the response with device registration details.
     * @throws CustomException If an error occurs during device registration.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@RequestBody @Valid DeviceRegisterRequest myDto) throws CustomException {
        Device myDevice = deviceService.registerDevice(myDto);
        return ResponseEntity.ok(ResponseBuilder.registerDeviceResponse(myDevice));
    }

    /**
     * Adds a device to a house.
     *
     * @param myDto AddDeviceRequest instance containing data for adding device to a house.
     * @return ResponseEntity containing the response with device addition details.
     * @throws CustomException If an error occurs during device addition.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addDeviceToHouse(@RequestBody @Valid AddDeviceRequest myDto) throws CustomException {
        Device myDevice = deviceService.addDeviceToHouse(myDto);
        return ResponseEntity.ok(ResponseBuilder.addDeviceToHouseResponse(myDevice));
    }
}
