package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.exception.CustomException;

public interface DeviceService {
    Device registerDevice(DeviceRegisterRequest myDto) throws CustomException;

    Device addDeviceToHouse(AddDeviceRequest myDto) throws CustomException;
}
