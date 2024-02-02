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

    public void addAll(AllDTO allDTO) {
        UserService userService = new UserService();
        User user = userService.mapUserDTOToUser(allDTO.getUserDTO());
        ShiftTypeService shiftTypeService = new ShiftTypeService();
        ShiftType shiftType = shiftTypeService.mapShiftTypeDTOToShiftType(allDTO.getShiftTypeDTO());
        ShiftService shiftService = new ShiftService();
        Shift shift = shiftService.mapShiftDTOToShift(allDTO.getShiftDTO());
        ShiftUserService shiftUserService = new ShiftUserService();
        ShiftUser shiftUser = shiftUserService.mapShiftUserDTOToShiftUser(allDTO.getShiftUserDTO());

        userDAO.saveUser(user);
        shiftTypeDAO.saveShiftType(shiftType);
        shiftDAO.saveShift(shift);
        shiftUserDAO.saveShiftUser(shiftUser);
    }
}