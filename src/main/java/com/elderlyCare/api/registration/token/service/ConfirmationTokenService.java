package com.elderlyCare.api.registration.token.service;

import com.elderlyCare.api.registration.token.entity.ConfirmationRegistrationToken;

public interface ConfirmationTokenService {


    void savaRegistrationToken(ConfirmationRegistrationToken token);

    String confirmToken(String token);
}
