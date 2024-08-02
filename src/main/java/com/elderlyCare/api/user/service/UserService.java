package com.elderlyCare.api.user.service;

import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.user.dto.*;

import java.util.List;

public interface UserService {

    List<ApplicationUserResponseDTO> getApplicationUser();

    HttpResponsesMessage saveContact(ApplicationContactDT0 applicationContactDT0);

    HttpResponsesMessage deleteUser(String userId);

    HttpResponsesMessage resetUserPassword(EmailRequestDTO email);

    ApplicationUserResponseDTO getApplicationUserFromId(UserDetailsDTO userDetailsDTO);
}
