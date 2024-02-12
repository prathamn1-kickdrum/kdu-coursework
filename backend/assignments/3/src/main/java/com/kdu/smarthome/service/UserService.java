package com.kdu.smarthome.service;

import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User getUserByUserName(String username) throws CustomException;

    User getUserByToken() throws CustomException;
    boolean isUserAssociatedWithToken(String userName) throws CustomException;

    boolean isUserRegistered(String username) throws CustomException;
}