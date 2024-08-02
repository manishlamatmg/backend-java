package com.elderlyCare.api.email.repository;


import com.elderlyCare.api.email.entity.EmailTemplateType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailTemplateTypeRepository extends JpaRepository<EmailTemplateType, String> {
}
