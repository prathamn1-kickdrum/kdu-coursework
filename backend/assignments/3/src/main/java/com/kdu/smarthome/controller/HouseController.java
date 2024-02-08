package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequest;
import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.exception.GlobalExceptionHandler;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.util.ExceptionType;
import com.kdu.smarthome.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/house")
public class HouseController {
    private final GlobalExceptionHandler handler;
    private final HouseService houseService;

    @PostMapping("/")
    public ResponseEntity<String> registerHouse(@RequestBody HouseRegisterRequest myDto) {
        House myHouse = houseService.registerHouse(myDto);
        return ResponseEntity.ok(ResponseBuilder.getHouseRegisterResponse(myHouse));
    }

    @GetMapping("/")
    public  ResponseEntity<String> getHouseList() {
        List<House> houseList = houseService.gethouseList();
        return ResponseEntity.ok(ResponseBuilder.getAllHousesResponse(houseList));
    }

    @PutMapping("/")
    public  ResponseEntity<GeneralResponseDto> updateHouseAddress(@RequestBody UpdateAddressRequest myDto) {
        return null;
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<String> userToHouse(@PathVariable long houseId, AddUserToHouseRequest myDto) {
        User myUser = houseService.addUserToHouse(houseId,myDto);
        if(myUser==null) {
            return handler.handleCustomException(new CustomException("Invalid token", ExceptionType.InvalidIssuer, HttpStatus.BAD_REQUEST));
        }
        return ResponseEntity.ok(ResponseBuilder.addUserToHouseResponse(myUser));
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<GeneralResponseDto> getHouseDetails(@PathVariable long houseId) {
        return null;
    }








}
