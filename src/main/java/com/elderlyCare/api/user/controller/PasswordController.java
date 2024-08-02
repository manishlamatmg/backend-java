package com.elderlyCare.api.user.controller;

import com.elderlyCare.api.aluthProvider.UserProvider;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.user.dto.EmailRequestDTO;
import com.elderlyCare.api.user.dto.PasswordDTO;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.entity.Password;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import com.elderlyCare.api.user.respository.PasswordRepository;
import com.elderlyCare.api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/passwords")
public class PasswordController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;
    public PasswordController(UserService userService,
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

    @PostMapping("/resetPasswordRequest")
    public HttpResponsesMessage requestPasswordRequest(@RequestBody EmailRequestDTO email){
        if(email.getEmail() == null || email.getEmail().isBlank()) {
            throw new ElderlyCareException("Please provide information", HttpStatus.BAD_REQUEST);
        }
        return userService.resetUserPassword(email);
    }


    @PostMapping("/resetPassword")
    public HttpResponsesMessage resetPassword(@RequestBody PasswordDTO passwordDTO){
        Password resetToken = passwordRepository.findByToken(passwordDTO.getToken());

        if( resetToken != null && !resetToken.getExpiryDate().isBefore(ZonedDateTime.now())){
            ApplicationUser applicationUser = resetToken.getApplicationUser();
            boolean matches = new BCryptPasswordEncoder().matches(passwordDTO.getPassword(), applicationUser.getPassword() == null ? " " : applicationUser.getPassword());
            if(!matches) {
                applicationUser.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
                applicationUserRepository.save(applicationUser);
                passwordRepository.delete(resetToken);
                return HttpResponsesMessage.builder().message("Password reset successfully").build();
            } else
                throw new ElderlyCareException("You have provided old password !!!", HttpStatus.BAD_REQUEST);
        } else
            return HttpResponsesMessage.builder().message("Invalid token or Token is already expired").status(HttpStatus.BAD_REQUEST).build();
    }
}
