package com.elderlyCare.api.algorithm;

import com.elderlyCare.api.aluthProvider.UserProvider;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.base.Message;
import com.elderlyCare.api.medical_Information.dto.UserInformationRequestDTO;
import com.elderlyCare.api.medical_Information.entity.UserInformation;
import com.elderlyCare.api.medical_Information.repository.MedicalInformationRepository;
import com.elderlyCare.api.medical_Information.service.MedicalInformationService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class AlgorithmController {
    private final MedicalInformationService userInformationService;
    private final UserProvider userProvider;
    private final MedicalInformationRepository medicalInformationRepository;

    public AlgorithmController(MedicalInformationService userInformationService,
                               UserProvider userProvider,
                               MedicalInformationRepository medicalInformationRepository) {
        this.userInformationService = userInformationService;
        this.userProvider = userProvider;
        this.medicalInformationRepository = medicalInformationRepository;
    }

    @RequestMapping(value = "api/v1/algorithms", method = RequestMethod.POST)
    public HttpResponsesMessage saveUserInformation(@RequestBody UserInformationRequestDTO userInfo) throws FileNotFoundException {
        return userInformationService.saveUserInformation(userInfo);
    }

}
