package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddRoomRequest;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.CustomException;

public interface RoomService {
    Room getRoomById(long roomId) throws CustomException;
    Room roomToHouse(long houseId, AddRoomRequest myDto) throws CustomException;
    boolean isRoomPresent(long roomId, long houseId) throws CustomException;
}
