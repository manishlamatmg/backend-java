package com.elderlyCare.api.registration.controller;


import com.elderlyCare.api.registration.dto.RegistrationRequestDTO;
import com.elderlyCare.api.registration.service.RegistrationService;
import com.elderlyCare.api.registration.token.service.ConfirmationTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registrations")
public class SignUpController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
    public SignUpController(RegistrationService registrationService,
                            ConfirmationTokenService confirmationTokenService){
        this.registrationService = registrationService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping
    public ResponseEntity<?> registrationUser(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        return ResponseEntity.ok().body(registrationService.registerUser(registrationRequestDTO));
    }

    @GetMapping
    public String confirmToken(@RequestParam("token") String token){
        return confirmationTokenService.confirmToken(token);
    }
}
