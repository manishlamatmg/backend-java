package com.elderlyCare.api.user.service.impl;

import com.elderlyCare.api.aluthProvider.UserDetailsDTO;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.email.dto.EmailFieldDTO;
import com.elderlyCare.api.email.mapper.EmailMapper;
import com.elderlyCare.api.email.service.impl.EmailConverter;
import com.elderlyCare.api.email.service.impl.EmailSenderImpl;
import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.user.dto.*;
import com.elderlyCare.api.user.entity.ApplicationContact;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.entity.Password;
import com.elderlyCare.api.user.mapper.UserMapper;
import com.elderlyCare.api.user.respository.ApplicationContactRepository;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import com.elderlyCare.api.user.respository.PasswordRepository;
import com.elderlyCare.api.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationContactRepository applicationContactRepository;
    private final PasswordRepository passwordRepository;
    private final EmailSenderImpl emailSender;


    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(ApplicationUserRepository applicationUserRepository,
                           ApplicationContactRepository applicationContactRepository,
                           PasswordEncoder passwordEncoder,
                           PasswordRepository passwordRepository,
                           EmailSenderImpl emailSender){
        this.applicationUserRepository = applicationUserRepository;
        this.applicationContactRepository = applicationContactRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordRepository = passwordRepository;
        this.emailSender = emailSender;
    }


    @Override
    public List<ApplicationUserResponseDTO> getApplicationUser() {

        List<ApplicationUserResponseInterface> applicationUsersAndContact =
                applicationUserRepository.getApplicationUsersAndContact();

        List<ApplicationUserResponseInterface> list = new ArrayList<>();
        for(ApplicationUserResponseInterface userResponseInterface : applicationUsersAndContact) {

            ApplicationUserResponseInterface applicationUserResponseInterface
                    = new ApplicationUserResponseInterface() {
                @Override
                public String getFirstName() {
                    return userResponseInterface.getFirstName();
                }

                @Override
                public String getLastName() {
                    return userResponseInterface.getLastName();
                }

                @Override
                public String getEmail() {
                    return userResponseInterface.getEmail();
                }

            };
            list.add(applicationUserResponseInterface);
        }

        List<ApplicationUserResponseDTO> collect =
                list.stream().map(applicationData -> UserMapper.toResponseDTO(applicationData)).collect(Collectors.toList());
        return collect ;

    }

    @Override
    public HttpResponsesMessage saveContact(ApplicationContactDT0 applicationContactDT0) {
        ApplicationContact applicationContact = UserMapper.toEntity(applicationContactDT0);
        applicationContactRepository.save(applicationContact);
        return HttpResponsesMessage.builder()
                .message("Contact Created successfully")
                .build();
    }

    @Override
    public HttpResponsesMessage deleteUser(String userId) {

        ApplicationUser applicationUsersById = applicationUserRepository.getApplicationUsersById();
        if(applicationUsersById == null){
            throw new ElderlyCareException("ApplicationUserId is null", HttpStatus.BAD_REQUEST);
        }
        applicationUserRepository.delete(applicationUsersById);
        return HttpResponsesMessage.builder().message("User Delete Successfully").build();

    }

    @Transactional
    @Override
    public HttpResponsesMessage resetUserPassword(EmailRequestDTO email) {

        ApplicationUser applicationUser = applicationUserRepository.getApplicationUsersByEmailAndId(email.getEmail())
                .orElseThrow(() -> new ElderlyCareException("User email not registered", HttpStatus.BAD_REQUEST));

        if(applicationUser != null){
            String token = generateToken();
            ZonedDateTime zonedDateTime = ZonedDateTime.now().plusMinutes(14);
            Password password = new Password();
            password.setToken(token);
            password.setExpiryDate(zonedDateTime);
            password.setEmail(email.getEmail());
            password.setApplicationUser(applicationUser);
            passwordRepository.save(password);

            EmailFieldDTO emailFieldDTO = EmailMapper.emailSendToChangePassword(applicationUser.getEmail(), token);
            var emailDTO = EmailConverter.sendEmailToChangePassword(emailFieldDTO);
            emailSender.sendEmail(emailDTO);

        }
        return HttpResponsesMessage.builder().message("Token send to email").build();
    }

    private String generateToken(){
        Random random = new Random();
        int randomNumber = 1000  + random.nextInt(9000);
        return String.valueOf(randomNumber);
    }

    @Override
    public ApplicationUserResponseDTO getApplicationUserFromId(UserDetailsDTO userDetailsDTO) {
        ApplicationResponseDTO applicationUsers = applicationUserRepository.getApplicationUsers(userDetailsDTO.getApplicationUserId());
        ApplicationUserResponseDTO applicationUserResponseDTO = UserMapper.userResponseDTO(applicationUsers);
        return applicationUserResponseDTO;
    }

}
