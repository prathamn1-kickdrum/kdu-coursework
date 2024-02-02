package com.example.spring.handson.controller;


import com.example.jdbc.dto.*;
import com.example.spring.handson.dto.*;
import com.example.spring.handson.model.Shift;
import com.example.spring.handson.model.ShiftType;
import com.example.spring.handson.model.ShiftUser;
import com.example.spring.handson.model.User;
import com.example.jdbc.service.*;
import com.example.spring.handson.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UserController {
   private final UserService userService;

   private final ShiftTypeService shiftTypeService;

   private final ShiftUserService shiftUserService;

   private final ShiftService shiftService;

   private final AllService allService;

   private final TenantService tenantService;

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUser(){
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/user/search")
    public ResponseEntity<List<User>> getUserByName(@RequestParam("name") String name){
        try{
            List<User> users = userService.getUserByName(name);
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/search/{id}")
    public User getUserById(@PathVariable String id){
        UUID uid = UUID.fromString(id);
        return  userService.getUserById(uid);
    }

    @PostMapping("/add/user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user){
        userService.addUser(user);
        return ResponseEntity.ok("user addedd succes");
    }

    @PutMapping("/update/user/{id}")
    public int updateUserName(@PathVariable UUID id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id, userDTO.getUserName());
    }


    @PostMapping("/add/shiftType")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftType){
        String message = "Added shift type";
        shiftTypeService.addShiftType(shiftType);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shiftType/search/{id}")
    public ShiftType getShiftTypeById(@PathVariable UUID id){
        return shiftTypeService.getShiftTypeById(id);
    }


    @PostMapping("/add/shiftUser")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO shiftUser){
        String message = "Add the shift user success";
        shiftUserService.addShiftUser(shiftUser);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shiftUser/search/{id}")
    public ShiftUser getShiftUserById(@PathVariable  UUID id){
        return shiftUserService.getShiftUserById(id);
    }



    @PostMapping("/add/shift")
    public ResponseEntity<String> addShift(@RequestBody ShiftDTO shift){
        String message = "add the shift user success";
        shiftService.addShift(shift);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shift/search/{id}")
    public Shift getShiftById(@PathVariable UUID id){
        return shiftService.getShiftById(id);
    }


    @PostMapping("/add/all")
    public ResponseEntity<String> addAllValues(@RequestBody AllDTO allDTO)
    {
        allService.addAll(allDTO);
        return ResponseEntity.ok("All the Values Added Successfully");
    }

    @PostMapping("/add/tenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO)
    {
        tenantService.addTenant(tenantDTO);
        return ResponseEntity.ok("Tenant Added Successfully");
    }

}
