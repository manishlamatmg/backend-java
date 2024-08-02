package com.elderlyCare.api.medical_Information.dto;

import com.elderlyCare.api.medical_Information.entity.AgeStatus;
import com.elderlyCare.api.medical_Information.entity.Diabetes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserInformationRequestDTO {
    private int age;
    private String medicine;
    private BloodPressure bloodPressure;
    private Cholesterol_Label cholesterol_label;
    private Weight weight;
    private Time morningMedication;
    private Time afterNoonMedication;
    private Time eveningMedication;
}
