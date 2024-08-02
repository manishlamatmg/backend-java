package com.elderlyCare.api.medical_Information.service;

import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.medical_Information.dto.UserInformationRequestDTO;

import java.io.FileNotFoundException;

public interface MedicalInformationService {
    HttpResponsesMessage saveUserInformation(UserInformationRequestDTO userInformationRequestDTO) throws FileNotFoundException;
}
