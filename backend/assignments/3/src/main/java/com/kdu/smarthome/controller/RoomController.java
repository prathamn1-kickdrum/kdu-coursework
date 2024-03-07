package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.service.RoomService;
import com.kdu.smarthome.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class for handling room-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    /**
     * Adds a room to a house.
     *
     * @param houseId The ID of the house to which the room is to be added.
     * @param myDto AddRoomRequest instance containing data for adding a room to the house.
     * @return ResponseEntity containing the response with added room details.
     * @throws CustomException If an error occurs during room addition.
     */
    @PostMapping
    public ResponseEntity<String> addRoomToHouse(@RequestParam @Valid long houseId, @RequestBody @Valid AddRoomRequest myDto) throws CustomException {
        Room myRoom = roomService.roomToHouse(houseId,myDto);
        return ResponseEntity.ok(ResponseBuilder.addRoomToHouseResponse(myRoom));
    }
}
