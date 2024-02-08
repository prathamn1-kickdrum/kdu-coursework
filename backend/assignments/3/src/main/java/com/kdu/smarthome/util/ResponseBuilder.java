package com.kdu.smarthome.util;

import com.kdu.smarthome.dto.response.GeneralResponseDto;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.List;

public class ResponseBuilder {

    private ResponseBuilder(){}
    public static String getHouseRegisterResponse(House myHouse) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("House registered successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("house",myHouse);
        return myDto.toJsonString();
    }

    public static String getAllHousesResponse(List<House> houseList) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("Houses fetched successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("houses",houseList);
        return myDto.toJsonString();
    }

    public static String addUserToHouseResponse(User myUser) {
        GeneralResponseDto myDto = GeneralResponseDto.builder()
                .message("user added to house successfully")
                .status(HttpStatus.OK)
                .additionalProperties(new HashMap<>())
                .build();
        myDto.addProperty("user",myUser);
        return myDto.toJsonString();
    }
}
