package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.DeviceRegisterRequest;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.service.DeviceService;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.service.RoomService;
import com.kdu.smarthome.service.UserService;
import com.kdu.smarthome.util.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation of the DeviceService interface providing methods for managing devices.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final RoomService roomService;
    private final UserService userService;
    private final HouseService houseService;

    /**
     * Retrieves a device by its kickston ID.
     *
     * @param kickstonId The kickston ID of the device.
     * @return The Device object.
     * @throws CustomException If the device ID is invalid.
     */
    public Device getDeviceById(String kickstonId) throws CustomException {
        return deviceRepository.findByKickstonId(kickstonId)
                .orElseThrow(() -> new CustomException("Invalid Device Id", ExceptionType.InvalidDeviceException, HttpStatus.BAD_REQUEST));
    }

    /**
     * Registers a device.
     *
     * @param myDto The DeviceRegisterRequest containing device registration details.
     * @return The registered Device object.
     * @throws CustomException If the device is unavailable or credentials are invalid.
     */
    @Override
    public Device registerDevice(DeviceRegisterRequest myDto) throws CustomException {
        Device myDevice = getDeviceById(myDto.getKickstonId());
        if (!myDevice.getDeviceUsername().equals(myDto.getDeviceUsername())) {
            throw new CustomException("Device unavailable", ExceptionType.InvalidDeviceCredentialException, HttpStatus.BAD_REQUEST);
        }
        if (!myDevice.getDevicePassword().equals(myDto.getDevicePassword())) {
            throw new CustomException("Invalid device credentials to register", ExceptionType.InvalidDeviceCredentialException, HttpStatus.UNAUTHORIZED);
        }
        myDevice.setRegistered(true);
        return deviceRepository.save(myDevice);
    }

    /**
     * Adds a device to a room in a house.
     *
     * @param myDto The AddDeviceRequest containing device and room details.
     * @return The updated Device object.
     * @throws CustomException If the device is not registered or room/house IDs are invalid.
     */
    @Override
    public Device addDeviceToHouse(AddDeviceRequest myDto) throws CustomException {
        Device myDevice = getDeviceById(myDto.getKickstonId());
        if (!myDevice.isRegistered()) {
            throw new CustomException("Device not registered", ExceptionType.DeviceNotRegisteredException, HttpStatus.BAD_REQUEST);
        } else if (!roomService.isRoomPresent(Long.parseLong(myDto.getRoomId()), Long.parseLong(myDto.getHouseId()))) {
            throw new CustomException("Invalid Room or House Id", ExceptionType.NoRoomInHouseException, HttpStatus.BAD_REQUEST);
        }
        Room myRoom = roomService.getRoomById(Long.parseLong(myDto.getRoomId()));
        myRoom.getDevices().add(myDevice);
        myDevice.setRoom(myRoom);
        return deviceRepository.save(myDevice);
    }
}
