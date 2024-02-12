package com.kdu.smarthome.util;

import com.kdu.smarthome.dto.response.GeneralResponseDto;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.entity.User;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for building response messages.
 */
@SuppressWarnings("all")
public class ResponseBuilder {

    private ResponseBuilder() {
    }

    /**
     * Builds a response message for registering a house.
     *
     * @param myHouse The registered house.
     * @return The response message.
     */
    public static String getHouseRegisterResponse(House myHouse) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("House registered successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        Map<String, Object> houseMap = new HashMap<>();
        houseMap.put("id", String.valueOf(myHouse.getHouseId()));
        myDto.addProperty("house", houseMap);
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for fetching all houses.
     *
     * @param houseList The list of houses.
     * @return The response message.
     */
    public static String getAllHousesResponse(List<House> houseList) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Houses fetched successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("houses", houseList.toString());
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for adding a user to a house.
     *
     * @param myUser The added user.
     * @return The response message.
     */
    public static String addUserToHouseResponse(User myUser) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("User added to house successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("user", myUser);
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for fetching house details.
     *
     * @param myHouse The house.
     * @return The response message.
     */
    public static String getHouseDetailsResponse(House myHouse) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("House details fetched successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("roomsAndDevices", myHouse);
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for updating house address.
     *
     * @param myHouse The updated house.
     * @return The response message.
     */
    public static String updateHouseAddressResponse(House myHouse) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("House address updated successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("house", myHouse.toString());
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for adding a room to a house.
     *
     * @param myRoom The added room.
     * @return The response message.
     */
    public static String addRoomToHouseResponse(Room myRoom) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Room added to house successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        Map<String, Object> roomMap = new HashMap<>();
        roomMap.put("id", String.valueOf(myRoom.getRoomId()));
        myDto.addProperty("room", roomMap);
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for adding a device to inventory.
     *
     * @param myDevice The added device.
     * @return The response message.
     */
    public static String addDeviceToInventoryResponse(Device myDevice) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Item added to inventory successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("device", myDevice.toString());
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for displaying the inventory.
     *
     * @param deviceList The list of devices in inventory.
     * @return The response message.
     */
    public static String displayInventoryResponse(List<Device> deviceList) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Inventory fetched successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("inventory", deviceList.toString());
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for registering a device.
     *
     * @param myDevice The registered device.
     * @return The response message.
     */
    public static String registerDeviceResponse(Device myDevice) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Device Registered successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("device", myDevice.toString());
        return myDto.toJsonString();
    }

    /**
     * Builds a response message for adding a device to a house.
     *
     * @param myDevice The added device.
     * @return The response message.
     */
    public static String addDeviceToHouseResponse(Device myDevice) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Device added to house successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("device", myDevice.toString());
        return myDto.toJsonString();
    }
}
