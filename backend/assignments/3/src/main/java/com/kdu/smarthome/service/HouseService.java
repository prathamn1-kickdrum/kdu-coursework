package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;

import java.util.List;

public interface HouseService {
    House registerHouse(HouseRegisterRequest myDto) throws CustomException;

    House getHouseById(long houseId) throws CustomException;

    List<House> gethouseList();

    User addUserToHouse(long houseId, AddUserToHouseRequest myDto) throws CustomException;

    House updateAddress(long houseId, UpdateAddressRequest myDto) throws CustomException;

    void saveHouse(House myHouse) throws CustomException;
}
