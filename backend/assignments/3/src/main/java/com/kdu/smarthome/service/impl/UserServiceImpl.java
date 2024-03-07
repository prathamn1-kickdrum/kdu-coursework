package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.exception.GlobalExceptionHandler;
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

/**
 * Implementation of UserService interface providing user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GlobalExceptionHandler handler;

    /**
     * Retrieves a UserDetails service instance.
     *
     * @return UserDetails service instance.
     */
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
                var user = userRepository.findByUsername(userName);
                if (user.isPresent()) {
                    return user.get();
                } else {
                    throw new UsernameNotFoundException("Username not found in database");
                }
            }
        };
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username to search for.
     * @return The user with the specified username.
     * @throws CustomException If the user is not found.
     */
    public User getUserByUserName(String username) throws CustomException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("No user present", ExceptionType.NoUserFoundException, HttpStatus.BAD_REQUEST));

    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The currently authenticated user.
     * @throws CustomException If the authentication context is null.
     */
    public User getUserByToken() throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String userName = userDetails.getUsername();
            return getUserByUserName(userName);
        } else {
            throw new CustomException("Authentication context null", ExceptionType.AuthenticationContextNullException, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Checks if a user is registered.
     *
     * @param username The username to check.
     * @return true if the user is registered, otherwise false.
     * @throws CustomException If an error occurs.
     */
    public boolean isUserRegistered(String username) throws CustomException {
        return getUserByUserName(username) != null;
    }

    /**
     * Checks if the authenticated user is associated with the provided username.
     *
     * @param userName The username to check.
     * @return true if the authenticated user is associated with the provided username, otherwise false.
     * @throws CustomException If an error occurs.
     */
    public boolean isUserAssociatedWithToken(String userName) throws CustomException {
        return userName.equals(getUserByToken().getUsername());
    }

}
