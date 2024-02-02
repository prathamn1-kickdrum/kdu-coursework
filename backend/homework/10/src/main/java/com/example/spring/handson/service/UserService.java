package com.example.spring.handson.service;

import com.example.spring.handson.dto.UserDto;
import com.example.spring.handson.exception.custom.NoUserFoundException;
import com.example.spring.handson.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() throws NoUserFoundException {
        try {
            List<UserDto> users = userRepository.getAllUsers();
            if(users.isEmpty()){
                throw new NoUserFoundException("No users found in the database");
            }
            return users;
        } catch (NoUserFoundException e) {
            throw new NoUserFoundException(e.getMessage());
        }
    }

    public UserDto getUserByName(String name) throws NoUserFoundException{
        try {
            UserDto user = userRepository.getUserByName(name);
            if (user != null) {
                return user;
            }
            throw new NoUserFoundException("No user found with name " + name);
        } catch (NoUserFoundException e) {
            throw new NoUserFoundException(e.getMessage());
        }
    }

    public UserDto addUser(UserDto userDto) {
        try {
            userRepository.addUser(userDto);
            return userDto;
        } catch (Exception e) {
            throw new InvalidParameterException("Error occurred while adding user");
        }
    }
}
