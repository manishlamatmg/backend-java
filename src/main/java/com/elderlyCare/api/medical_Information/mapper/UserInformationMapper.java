package com.elderlyCare.api.medical_Information.mapper;

import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.medical_Information.dto.UserInformationRequestDTO;
import com.elderlyCare.api.medical_Information.entity.UserInformation;

public class UserInformationMapper {

    public static UserInformation toEntity(UserInformationRequestDTO userInformationRequestDTO) {
        return UserInformation.builder()
                .age(userInformationRequestDTO.getAge())
                .bloodPressure(userInformationRequestDTO.getBloodPressure())
                .cholesterol_label(userInformationRequestDTO.getCholesterol_label())
                .weight(userInformationRequestDTO.getWeight())
                .medicine(userInformationRequestDTO.getMedicine())
                .morningMedication(userInformationRequestDTO.getMorningMedication())
                .afterNoonMedication(userInformationRequestDTO.getAfterNoonMedication())
                .eveningMedication(userInformationRequestDTO.getEveningMedication())
                .build();
    }
}
