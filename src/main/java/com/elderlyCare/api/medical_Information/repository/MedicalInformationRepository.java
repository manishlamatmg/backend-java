package com.elderlyCare.api.medical_Information.repository;

import com.elderlyCare.api.medical_Information.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalInformationRepository extends JpaRepository<UserInformation, String> {

    @Query(value = "select * from user_information where application_user_id = :userId", nativeQuery = true)
    UserInformation getUserInformationByApplicationUserId(@Param("userId") String userId);
}
