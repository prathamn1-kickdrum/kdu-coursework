package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.AddUserToHouseRequest;
import com.kdu.smarthome.dto.request.HouseRegisterRequest;
import com.kdu.smarthome.dto.request.UpdateAddressRequest;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.service.UserService;
import com.kdu.smarthome.util.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the HouseService interface providing methods for managing houses.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final UserService userService;

    /**
     * Retrieves a house by its ID.
     *
     * @param houseId The ID of the house.
     * @return The House object.
     * @throws CustomException If the house ID is invalid.
     */
    public House getHouseById(long houseId) throws CustomException {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new CustomException("Invalid House Id", ExceptionType.InvalidHouseIdException));
    }

    /**
     * Checks if the current user is the admin of the given house.
     *
     * @param house The House object to check.
     * @return True if the current user is the admin, false otherwise.
     * @throws CustomException If an error occurs while checking.
     */
    public boolean isHouseAdmin(House house) throws CustomException {
        return (house.getHouseAdmin().equals(userService.getUserByToken().getUsername()));
    }

    /**
     * Saves a house.
     *
     * @param myHouse The House object to save.
     * @throws CustomException If an error occurs while saving.
     */
    public void saveHouse(House myHouse) throws CustomException {
        try {
            houseRepository.save(myHouse);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), ExceptionType.SavingHouseException, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Registers a new house.
     *
     * @param myDto The HouseRegisterRequest containing house registration details.
     * @return The registered House object.
     * @throws CustomException If an error occurs during registration.
     */
    public House registerHouse(HouseRegisterRequest myDto) throws CustomException {
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

    /**
     * Retrieves a list of all houses.
     *
     * @return A list of House objects.
     */
    public List<House> gethouseList() {
        return houseRepository.findAll();
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId The ID of the house.
     * @param myDto   The AddUserToHouseRequest containing user details.
     * @return The added User object.
     * @throws CustomException If an error occurs during the operation.
     */
    public User addUserToHouse(long houseId, AddUserToHouseRequest myDto) throws CustomException {
        House myHouse = getHouseById(houseId);
        User myUser = userService.getUserByUserName(myDto.getUsername());

        if (!isHouseAdmin(myHouse)) {
            throw new CustomException("Token not associated with admin", ExceptionType.BadLoginCredentialsException, HttpStatus.UNAUTHORIZED);
        } else if (myUser == null) {
            throw new CustomException("User not registered", ExceptionType.NoUserFoundException, HttpStatus.UNAUTHORIZED);
        }

        myHouse.addUserToHouse(myUser);
        saveHouse(myHouse);
        return myUser;
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId The ID of the house.
     * @param myDto   The UpdateAddressRequest containing the new address.
     * @return The updated House object.
     * @throws CustomException If the house ID is invalid or an error occurs during the operation.
     */
    @Override
    public House updateAddress(long houseId, UpdateAddressRequest myDto) throws CustomException {
        House myHouse = getHouseById(houseId);
        myHouse.setAddress(myDto.getAddress());
        saveHouse(myHouse);
        return myHouse;
    }
}
