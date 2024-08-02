package com.elderlyCare.api.user.controller;

import com.elderlyCare.api.aluthProvider.UserProvider;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.user.dto.*;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.entity.Password;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import com.elderlyCare.api.user.respository.PasswordRepository;
import com.elderlyCare.api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final  UserService userService;
    private final UserProvider userProvider;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;
    public UserController(UserService userService,
                          UserProvider userProvider,
                          PasswordRepository passwordRepository,
                          PasswordEncoder passwordEncoder,
                          ApplicationUserRepository applicationUserRepository){
        this.userService = userService;
        this.userProvider = userProvider;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping("allUsers")
    public List<ApplicationUserResponseDTO> getAllApplicationUser(){
        return userService.getApplicationUser();
    }

    @GetMapping()
    public ApplicationUserResponseDTO getApplicationUser(){
        return userService.getApplicationUserFromId(userProvider.userDetails());
    }

    @DeleteMapping("/{userId}")
    public HttpResponsesMessage deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }


}
