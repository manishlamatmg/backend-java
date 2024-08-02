package com.elderlyCare.api.registration.token.service;

import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.registration.token.entity.ConfirmationRegistrationToken;
import com.elderlyCare.api.registration.token.repository.RegistrationTokenRepository;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    private final RegistrationTokenRepository registrationTokenRepository;
    private final ApplicationUserRepository applicationUserRepository;
    public ConfirmationTokenServiceImpl(RegistrationTokenRepository registrationTokenRepository,
                                        ApplicationUserRepository applicationUserRepository){
        this.registrationTokenRepository = registrationTokenRepository;
        this.applicationUserRepository = applicationUserRepository;
    }
    @Override
    public void savaRegistrationToken(ConfirmationRegistrationToken token) {
        registrationTokenRepository.save(token);
    }

     @Override
    public String confirmToken(String token){

        ConfirmationRegistrationToken confirmationRegistrationToken = registrationTokenRepository.findByRegistrationToken(token)
                .orElseThrow(() -> new ElderlyCareException("Token not found", HttpStatus.BAD_REQUEST));
        if(confirmationRegistrationToken.getConfirmedAt() != null){
            throw  new ElderlyCareException("Email is already used",HttpStatus.BAD_REQUEST);
        }
        ZonedDateTime expiresAt = confirmationRegistrationToken.getExpiresAt();

        if(expiresAt.isBefore(ZonedDateTime.now())){
            throw new ElderlyCareException("Token is expired", HttpStatus.BAD_REQUEST);
        }
        confirmationRegistrationToken.setConfirmedAt(ZonedDateTime.now());
         ApplicationUser applicationUser = confirmationRegistrationToken.getApplicationUser();
         applicationUser.setIsActive(true);
         applicationUserRepository.save(applicationUser);
         return "        Your account is successfully activated !!!";
     }
}
