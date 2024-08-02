package com.elderlyCare.api.email.repository;

import com.elderlyCare.api.email.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, String> {

    EmailTemplate getEmailTemplateByEmailTemplateType_Name(String template);

}
