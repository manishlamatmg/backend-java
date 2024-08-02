package com.elderlyCare.api.registration.service;

import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.registration.dto.RegistrationRequestDTO;


public interface RegistrationService {
     HttpResponsesMessage registerUser(RegistrationRequestDTO registrationRequestDTO);
}
