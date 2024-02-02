package com.example.spring.handson.service;

import com.example.spring.handson.dao.ShiftUserDAO;
import com.example.spring.handson.dto.ShiftUserDTO;
import com.example.spring.handson.model.ShiftUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ShiftUserService {

    private final ShiftUserDAO shiftUserDAO;

    public void addShiftUser(ShiftUserDTO shiftUserDTO){
        ShiftUser shiftUser = mapShiftUserDTOToShiftUser(shiftUserDTO);
        shiftUserDAO.saveShiftUser(shiftUser);
    }

    public ShiftUser mapShiftUserDTOToShiftUser(ShiftUserDTO shiftUserDTO) {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.randomUUID());
        shiftUser.setShiftId(UUID.fromString(shiftUserDTO.getShiftId()));
        shiftUser.setUserId(UUID.fromString(shiftUserDTO.getUserId()));
        shiftUser.setTenantId(UUID.fromString(shiftUserDTO.getTenantId()));
        return shiftUser;
    }

    public ShiftUser getShiftUserById(UUID id){
        return shiftUserDAO.getShiftUserById(id);
    }

}
