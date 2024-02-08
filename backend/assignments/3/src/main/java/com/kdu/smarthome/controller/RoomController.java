package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.dto.response.GeneralResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @PostMapping("/")
    public ResponseEntity<GeneralResponseDto> addRoomToHouse(@RequestBody AddRoomRequest myDto) {
        return null;
    }
}
