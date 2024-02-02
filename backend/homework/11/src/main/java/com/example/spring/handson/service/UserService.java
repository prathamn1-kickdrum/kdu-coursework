package com.example.spring.handson.service;


import com.example.spring.handson.dao.UserDAO;
import com.example.spring.handson.dto.UserDTO;
import com.example.spring.handson.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserDAO userDAO;
    public void addUser(UserDTO userDTO){
        User user = mapUserDTOToUser(userDTO);
        userDAO.saveUser(user);
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName(userDTO.getUserName());
        user.setLoggedIn(userDTO.isLoggedIn());
        user.setTimeZone(userDTO.getTimeZone());
        user.setTenantId(UUID.fromString(userDTO.getTenantId()));
        return user;
    }

    public User getUserById(UUID id){
        return userDAO.getUserById(id);
    }

    public List<User> getAllUser(){
        return userDAO.getUsers();
    }

    public List<User> getUserByName(String name){
        return userDAO.getUserByName(name);
    }

    public int updateUser(UUID id, String name){
        return userDAO.updateNameForId(id,name);
    }


}
