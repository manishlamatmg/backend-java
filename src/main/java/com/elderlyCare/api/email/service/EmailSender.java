package com.elderlyCare.api.email.service;

import com.elderlyCare.api.email.dto.EmailDTO;

public interface EmailSender {

    void sendEmail(EmailDTO emailDTO);
}
