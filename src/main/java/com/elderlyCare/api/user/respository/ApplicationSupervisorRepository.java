package com.elderlyCare.api.user.respository;

import com.elderlyCare.api.user.entity.ApplicationSupervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationSupervisorRepository extends JpaRepository<ApplicationSupervisor, String> {
}
