package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;

public interface AuthenticationService {
    RegisterResponseDto registerAndLoginUser(UserRegisterRequest myDto);
    RegisterResponseDto loginUser(UserLoginRequest myDto);
    RegisterResponseDto refreshUser(UserRefreshRequest myDto);
}
