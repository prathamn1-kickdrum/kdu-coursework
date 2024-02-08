package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.service.UserService;
import com.kdu.smarthome.util.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    private final UserService userService;

    public House getHouseById(long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(()->new CustomException("Invalid House Id", ExceptionType.InvalidHouseIdException));
    }
    public boolean isHouseAdmin(long houseId) {
        House house = getHouseById(houseId);
        return !Objects.equals(house.getHouseAdmin(), userService.getUserByToken().getUsername());

    }

    public House registerHouse(HouseRegisterRequest myDto) {
        User user = userService.getUserByToken();
        House myHouse = House.builder()
                        .houseName(myDto.getHouseName())
                        .address(myDto.getAddress())
                        .houseAdmin(user.getUsername())
                        .users(new HashSet<>())
                        .build();
        myHouse.addUserToHouse(user);
        return houseRepository.save(myHouse);
    }

    public List<House> gethouseList() {
        return houseRepository.findAll();
    }

    public User addUserToHouse(long houseId,AddUserToHouseRequest myDto) throws CustomException {
        if(!(isHouseAdmin(houseId) && userService.isUserAssociatedWithToken(myDto.getUsername()))) {
            return null;
        }
        House myHouse = getHouseById(houseId);
        User myUser = userService.getUserByUserName(myDto.getUsername());
        myHouse.addUserToHouse(myUser);
        try {
            houseRepository.save(myHouse);
            return myUser;
        } catch (Exception e) {
            throw new CustomException(e.getMessage(),ExceptionType.SavingHouseException,HttpStatus.UNAUTHORIZED);
        }
    }

}
