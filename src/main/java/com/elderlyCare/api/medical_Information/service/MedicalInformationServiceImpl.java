package com.elderlyCare.api.medical_Information.service;

import com.elderlyCare.api.algorithm.C4_5;
import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.base.Message;
import com.elderlyCare.api.medical_Information.dto.UserInformationRequestDTO;
import com.elderlyCare.api.medical_Information.entity.UserInformation;
import com.elderlyCare.api.medical_Information.mapper.UserInformationMapper;
import com.elderlyCare.api.medical_Information.repository.MedicalInformationRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class MedicalInformationServiceImpl implements MedicalInformationService {
    private final MedicalInformationRepository medicalInformationRepository;
    public MedicalInformationServiceImpl(MedicalInformationRepository medicalInformationRepository){
        this.medicalInformationRepository = medicalInformationRepository;
    }
    @Override
    public HttpResponsesMessage saveUserInformation(UserInformationRequestDTO userInformationRequestDTO) throws FileNotFoundException {

        UserInformation entity = UserInformationMapper.toEntity(userInformationRequestDTO);
        UserInformation save = medicalInformationRepository.save(entity);
        int age = save.getAge();
        String message = C4_5.usingAlgorithmToRecommendDiet(age);
        return HttpResponsesMessage.builder()
                .message(message)
                .build();
    }
}
