package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.exception.CustomException;

public interface AuthenticationService {
    RegisterResponseDto registerAndLoginUser(UserRegisterRequest myDto) throws CustomException;
    RegisterResponseDto loginUser(UserLoginRequest myDto) throws CustomException;
    RegisterResponseDto refreshUser(UserRefreshRequest myDto) throws CustomException;
}
