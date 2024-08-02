package com.elderlyCare.api.role.repository;

import com.elderlyCare.api.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Query("select r from Role r where r.applicationUser.id = :applicationUserId")
    Set<Role> findRoleByApplicationUserId(@Param("applicationUserId") String applicationUserId);
}
