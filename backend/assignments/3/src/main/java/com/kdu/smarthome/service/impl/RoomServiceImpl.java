package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.service.RoomService;
import com.kdu.smarthome.util.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation of the RoomService interface that provides functionality related to rooms.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final HouseService houseService;
    private final RoomRepository roomRepository;

    /**
     * Saves a room entity.
     *
     * @param myRoom The room entity to be saved.
     * @return The saved room entity.
     * @throws CustomException If an error occurs during saving.
     */
    public Room saveRoom(Room myRoom) throws CustomException {
        try {
            return roomRepository.save(myRoom);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), ExceptionType.SavingHouseException, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Retrieves a room by its ID.
     *
     * @param roomId The ID of the room to retrieve.
     * @return The retrieved room entity.
     * @throws CustomException If the room ID is invalid.
     */
    public Room getRoomById(long roomId) throws CustomException {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new CustomException("Invalid room Id", ExceptionType.InvalidHouseIdException));
    }

    /**
     * Checks if a room is present in a given house.
     *
     * @param roomId  The ID of the room.
     * @param houseId The ID of the house.
     * @return True if the room is present in the house, false otherwise.
     * @throws CustomException If an error occurs during the operation.
     */
    public boolean isRoomPresent(long roomId, long houseId) throws CustomException {
        Room myRoom = getRoomById(roomId);
        return myRoom.getHouse().getHouseId() == houseId;
    }

    /**
     * Adds a room to a house.
     *
     * @param houseId The ID of the house.
     * @param myDto   The request DTO containing room information.
     * @return The added room entity.
     * @throws CustomException If an error occurs during the operation.
     */
    public Room roomToHouse(long houseId, AddRoomRequest myDto) throws CustomException {
        House myHouse = houseService.getHouseById(houseId);
        Room myRoom = Room.builder()
                .house(myHouse)
                .roomName(myDto.getRoomName())
                .build();
        myHouse.getRooms().add(myRoom);
        return saveRoom(myRoom);
    }
}
