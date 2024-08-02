package com.elderlyCare.api.user.respository;

import com.elderlyCare.api.user.entity.ApplicationContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationContactRepository extends JpaRepository<ApplicationContact, String> {
}
