package com.elderlyCare.api.user.mapper;

import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.user.dto.ApplicationContactDT0;
import com.elderlyCare.api.user.dto.ApplicationResponseDTO;
import com.elderlyCare.api.user.dto.ApplicationUserResponseDTO;
import com.elderlyCare.api.user.dto.ApplicationUserResponseInterface;
import com.elderlyCare.api.user.entity.ApplicationContact;
import com.elderlyCare.api.user.entity.ApplicationUser;

public class UserMapper {
    public static ApplicationUserResponseDTO toResponseDTO(ApplicationUserResponseInterface userResponseInterface){
        return ApplicationUserResponseDTO.builder()
                .firstName(userResponseInterface.getFirstName())
                .lastName(userResponseInterface.getLastName())
                .email(userResponseInterface.getEmail())
                .build();
    }

    public static ApplicationContact toEntity(ApplicationContactDT0 applicationContactDT0){
       return ApplicationContact.builder()
               .localAddress(applicationContactDT0.getLocalAddress())
               .phoneNumber(applicationContactDT0.getPhoneNumber())
               .phoneNumber(applicationContactDT0.getPhoneNumber())
               .district(applicationContactDT0.getDistrict())
               .userName(applicationContactDT0.getUserName())
               .message(applicationContactDT0.getMessage())
               .email(applicationContactDT0.getEmail())
               .build();
    }

    public static ApplicationUserResponseDTO userResponseDTO(ApplicationResponseDTO applicationResponseDTO){
        return ApplicationUserResponseDTO.builder()
                .firstName(applicationResponseDTO.getFirstName())
                .lastName(applicationResponseDTO.getLastName())
                .email(applicationResponseDTO.getEmail())
                .build();

    }

}
