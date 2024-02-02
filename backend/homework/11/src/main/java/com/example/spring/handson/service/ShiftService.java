package com.example.spring.handson.service;

import com.example.spring.handson.dao.ShiftDAO;
import com.example.spring.handson.dto.ShiftDTO;
import com.example.spring.handson.model.Shift;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Service
public class ShiftService {

    private final ShiftDAO shiftDAO;
    public void addShift(ShiftDTO shiftDTO){
        Shift shift = mapShiftDTOToShift(shiftDTO);

        shiftDAO.saveShift(shift);
    }

    public Shift mapShiftDTOToShift(ShiftDTO shiftDTO) {
        Shift shift = new Shift();
        shift.setId(UUID.randomUUID());
        shift.setShiftTypeId(UUID.fromString(shiftDTO.getShiftTypeId()));
        shift.setName(shiftDTO.getName());
        shift.setDateStart(LocalDate.parse(shiftDTO.getDateStart()));
        shift.setDateEnd(LocalDate.parse(shiftDTO.getDateEnd()));
        shift.setTimeStart(Time.valueOf(shiftDTO.getTimeStart()));
        shift.setTimeEnd(Time.valueOf(shiftDTO.getTimeEnd()));
        shift.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setTimeZone(shiftDTO.getTimeZone());
        shift.setTenantId(UUID.fromString(shiftDTO.getTenantId()));
        return shift;
    }

    public Shift getShiftById(UUID id){
        return shiftDAO.getShiftByid(id);
    }
}
