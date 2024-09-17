package com.example.spring.handson.service;

import com.example.spring.handson.dao.ShiftDAO;
import com.example.spring.handson.dao.ShiftTypeDAO;
import com.example.spring.handson.dao.ShiftUserDAO;
import com.example.spring.handson.dao.UserDAO;
import com.example.spring.handson.dto.AllDTO;
import com.example.spring.handson.model.Shift;
import com.example.spring.handson.model.ShiftType;
import com.example.spring.handson.model.ShiftUser;
import com.example.spring.handson.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AllService {
    private final UserDAO userDAO;
    private final ShiftDAO shiftDAO;
    private final ShiftTypeDAO shiftTypeDAO;
    private final ShiftUserDAO shiftUserDAO;
    private final UserService userService;
    private final ShiftTypeService shiftTypeService;
    private final ShiftService shiftService;
    private final ShiftUserService shiftUserService;
    public void addAll(AllDTO allDTO) {

        User user = userService.mapUserDTOToUser(allDTO.getUserDTO());

        ShiftType shiftType = shiftTypeService.mapShiftTypeDTOToShiftType(allDTO.getShiftTypeDTO());

        Shift shift = shiftService.mapShiftDTOToShift(allDTO.getShiftDTO());

        ShiftUser shiftUser = shiftUserService.mapShiftUserDTOToShiftUser(allDTO.getShiftUserDTO());

        userDAO.saveUser(user);
        shiftTypeDAO.saveShiftType(shiftType);
        shiftDAO.saveShift(shift);
        shiftUserDAO.saveShiftUser(shiftUser);
    }
}