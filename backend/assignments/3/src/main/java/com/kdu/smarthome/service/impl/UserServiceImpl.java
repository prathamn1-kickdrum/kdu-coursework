package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.service.UserService;
import com.kdu.smarthome.util.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                return userRepository.findByUsername(userName)
                        .orElseThrow(()->new CustomException("user not found",ExceptionType.NoUserFoundException, HttpStatus.NOT_FOUND));
            }
        };
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new CustomException("No User Present", ExceptionType.NoUserFoundException));
    }

    public User getUserByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String userName = userDetails.getUsername();
            return getUserByUserName(userName);
        }else {
            return null;
        }
    }

    public boolean isUserAssociatedWithToken(String userName) {
        return !Objects.equals(userName, getUserByToken().getUsername());
    }

}
