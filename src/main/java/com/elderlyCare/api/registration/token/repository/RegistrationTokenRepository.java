package com.elderlyCare.api.registration.token.repository;

import com.elderlyCare.api.registration.token.entity.ConfirmationRegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegistrationTokenRepository extends JpaRepository<ConfirmationRegistrationToken, String> {
    @Query("select crt from  ConfirmationRegistrationToken crt where crt.registrationToken= :token")
    Optional<ConfirmationRegistrationToken> findByRegistrationToken(@Param("token")String token);
}
