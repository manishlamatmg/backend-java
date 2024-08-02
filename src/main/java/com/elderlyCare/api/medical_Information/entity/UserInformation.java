package com.elderlyCare.api.medical_Information.entity;

import com.elderlyCare.api.base.IdentityNumber;
import com.elderlyCare.api.medical_Information.dto.BloodPressure;
import com.elderlyCare.api.medical_Information.dto.Cholesterol_Label;
import com.elderlyCare.api.medical_Information.dto.Weight;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInformation extends IdentityNumber {

    private int age;
    private String medicine;
    @Enumerated(EnumType.STRING)
    private BloodPressure bloodPressure;
    @Enumerated(EnumType.STRING)
    private Cholesterol_Label cholesterol_label;
    @Enumerated(EnumType.STRING)
    private Weight weight;
    private Time morningMedication;
    private Time afterNoonMedication;
    private Time eveningMedication;

}
