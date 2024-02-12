package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling house-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    /**
     * Registers a new house.
     *
     * @param myDto HouseRegisterRequest instance containing house registration data.
     * @return ResponseEntity containing the response with house registration details.
     * @throws CustomException If an error occurs during house registration.
     */
    @PostMapping
    public ResponseEntity<String> registerHouse(@RequestBody @Valid HouseRegisterRequest myDto) throws CustomException {
        House myHouse = houseService.registerHouse(myDto);
        return ResponseEntity.ok(ResponseBuilder.getHouseRegisterResponse(myHouse));
    }

    /**
     * Retrieves a list of all houses.
     *
     * @return ResponseEntity containing the response with the list of houses.
     * @throws JsonProcessingException If an error occurs while processing JSON.
     */
    @GetMapping
    public  ResponseEntity<String> getHouseList() throws JsonProcessingException {
        List<House> houseList = houseService.gethouseList();
        return ResponseEntity.ok(ResponseBuilder.getAllHousesResponse(houseList));
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId The ID of the house to be updated.
     * @param myDto UpdateAddressRequest instance containing the updated address data.
     * @return ResponseEntity containing the response with updated house details.
     * @throws CustomException If an error occurs during address update.
     */
    @PutMapping
    public  ResponseEntity<String> updateHouseAddress(@RequestParam long houseId, @RequestBody UpdateAddressRequest myDto) throws CustomException {
        House myHouse = houseService.updateAddress(houseId,myDto);
        return ResponseEntity.ok(ResponseBuilder.updateHouseAddressResponse(myHouse));
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId The ID of the house to which the user is to be added.
     * @param myDto AddUserToHouseRequest instance containing user addition data.
     * @return ResponseEntity containing the response with added user details.
     * @throws CustomException If an error occurs during user addition.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<String> userToHouse(@PathVariable long houseId,@RequestBody AddUserToHouseRequest myDto) throws CustomException {
        User myUser = houseService.addUserToHouse(houseId,myDto);
        return ResponseEntity.ok(ResponseBuilder.addUserToHouseResponse(myUser));
    }

    /**
     * Retrieves details of a specific house.
     *
     * @param houseId The ID of the house to retrieve details for.
     * @return ResponseEntity containing the response with house details.
     * @throws CustomException If an error occurs during house retrieval.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<String> getHouseDetails(@PathVariable long houseId) throws CustomException {
        House myHouse = houseService.getHouseById(houseId);
        return ResponseEntity.ok(ResponseBuilder.getHouseDetailsResponse(myHouse));
    }
}
