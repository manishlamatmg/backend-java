package com.elderlyCare.api.security.controller;

import com.elderlyCare.api.exception.AuthenticationError;
import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.security.dto.AuthenticationRequest;
import com.elderlyCare.api.security.dto.AuthenticationResponse;
import com.elderlyCare.api.security.service.JwtService;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(ApplicationUserRepository applicationUserRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager){
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> getLogin(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e){
            throw new AuthenticationError("Please provide valid email and password", HttpStatus.BAD_REQUEST);
        }
        var user = applicationUserRepository.getApplicationUsersByEmailAndIsActive(authenticationRequest.getEmail())
                .orElseThrow(() -> new ElderlyCareException("Token is not verified yet !", HttpStatus.BAD_REQUEST));
        String token = jwtService.generateToken(user.getEmail());
        AuthenticationResponse userDto = AuthenticationResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userId(user.getId())
                .token(token)
                .build();
        return ResponseEntity.ok().body(userDto);
    }
}
