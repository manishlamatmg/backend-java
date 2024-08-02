package com.elderlyCare.api.user.respository;


import com.elderlyCare.api.user.dto.ApplicationResponseDTO;
import com.elderlyCare.api.user.dto.ApplicationUserResponseInterface;
import com.elderlyCare.api.user.entity.ApplicationUser;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

    ApplicationUser findApplicationUsersByEmail(String email);

    @Query("select au from ApplicationUser au where au.email = :email and au.isActive = true")
    Optional<ApplicationUser> getApplicationUsersByEmailAndIsActive(@Param("email") String email);

    @Query("""
       select au.firstName as firstName,
              au.lastName as lastName,
              au.email as email
       from ApplicationUser au
""")
    List<ApplicationUserResponseInterface> getApplicationUsersAndContact();

    @Query("select au from ApplicationUser au where au.id = :userId")
    ApplicationUser getApplicationUsersById();

    @Query("select au from ApplicationUser au where au.email = :email and au.isActive = true")
    Optional<ApplicationUser> getApplicationUsersByEmailAndId(@Param("email") String email);


    @Query(""" 
    select au.firstName as firstName,
           au.lastName as lastName,
           au.email as email
           from ApplicationUser au
           where au.id = :applicationId
    """)
    ApplicationResponseDTO getApplicationUsers(@Param("applicationId") String applicationId);



    @Query("""
      select au from ApplicationUser au  where lower(au.email) = :email and au.isActive = :isActive
""")
    ApplicationUser findApplicationUsersByEmailAnAndIsActiveTrue(@Param("email") String email, @Param("isActive") boolean isActive);

}
