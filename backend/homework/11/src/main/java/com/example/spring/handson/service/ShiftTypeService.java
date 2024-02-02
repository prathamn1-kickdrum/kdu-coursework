package com.example.spring.handson.service;
import com.example.spring.handson.dao.ShiftTypeDAO;
import com.example.spring.handson.dto.ShiftTypeDTO;
import com.example.spring.handson.model.ShiftType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ShiftTypeService {

    private final ShiftTypeDAO shiftTypeDAO;

    public void addShiftType(ShiftTypeDTO shiftTypeDTO){
        ShiftType shiftType = mapShiftTypeDTOToShiftType(shiftTypeDTO);
        shiftTypeDAO.saveShiftType(shiftType);
    }

    public ShiftType mapShiftTypeDTOToShiftType(ShiftTypeDTO shiftTypeDTO) {
        ShiftType shiftType = new ShiftType();
        shiftType.setId(UUID.randomUUID());
        shiftType.setName(shiftTypeDTO.getName());
        shiftType.setDescription(shiftTypeDTO.getDescription());
        shiftType.setActive(shiftTypeDTO.isActive());
        shiftType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setTimeZone(shiftTypeDTO.getTimeZone());
        shiftType.setTenantId(UUID.fromString(shiftTypeDTO.getTenantId()));
        return shiftType;
    }

    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDAO.getShiftTypeById(id);
    }

}
