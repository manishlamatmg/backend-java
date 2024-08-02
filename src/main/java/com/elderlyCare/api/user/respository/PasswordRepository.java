package com.elderlyCare.api.user.respository;

import com.elderlyCare.api.user.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, String> {

    @Query("select p from Password p where p.token = :token and p.applicationUser.isActive = true")
    Password findByToken(@Param("token") String token);

}
