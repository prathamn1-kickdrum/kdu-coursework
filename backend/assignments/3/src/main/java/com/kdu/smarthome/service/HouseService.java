package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HouseService {
    House registerHouse(HouseRegisterRequest myDto);

    List<House> gethouseList();

    User addUserToHouse(long houseId, AddUserToHouseRequest myDto);
}
