package com.elderlyCare.api.registration.service;

import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.email.dto.EmailFieldDTO;
import com.elderlyCare.api.email.mapper.EmailMapper;
import com.elderlyCare.api.email.service.impl.EmailConverter;
import com.elderlyCare.api.email.service.impl.EmailSenderImpl;
import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.organization.entity.Organization;
import com.elderlyCare.api.organization.repository.OrganizationRepository;
import com.elderlyCare.api.registration.dto.RegistrationRequestDTO;
import com.elderlyCare.api.registration.token.entity.ConfirmationRegistrationToken;
import com.elderlyCare.api.registration.token.service.ConfirmationTokenService;
import com.elderlyCare.api.role.entity.Role;
import com.elderlyCare.api.role.repository.RoleRepository;
import com.elderlyCare.api.user.entity.ApplicationUser;
import com.elderlyCare.api.user.respository.ApplicationUserRepository;
import com.elderlyCare.api.util.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderImpl emailSenderImpl;

    private final OrganizationRepository organizationRepository;

    private final RoleRepository roleRepository;

    public RegistrationServiceImpl(EmailValidator emailValidator,
                                   ApplicationUserRepository applicationUserRepository,
                                   PasswordEncoder passwordEncoder,
                                   ConfirmationTokenService confirmationTokenService,
                                   EmailSenderImpl emailSenderImpl,
                                   OrganizationRepository organizationRepository,
                                   RoleRepository roleRepository) {
        this.emailValidator = emailValidator;
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderImpl = emailSenderImpl;
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public HttpResponsesMessage registerUser(RegistrationRequestDTO registrationRequestDTO) {

        ApplicationUser userByEmail = applicationUserRepository.findApplicationUsersByEmailAnAndIsActiveTrue(registrationRequestDTO.getEmail().toLowerCase(), true);
        if (userByEmail == null) {
            saveApplication(registrationRequestDTO);
        } else {
            throw new ElderlyCareException("user already exit", HttpStatus.BAD_REQUEST);
        }
        return HttpResponsesMessage.builder().message("Please verify through your email !!!").build();
    }

    private void saveApplication(RegistrationRequestDTO registrationRequestDTO) {
        // Saving Application User
        ApplicationUser applicationUser = ApplicationUser.builder()
                .firstName(registrationRequestDTO.getFirstName())
                .lastName(registrationRequestDTO.getLastName())
                .email(registrationRequestDTO.getEmail().toLowerCase())
                .password(passwordEncoder.encode(registrationRequestDTO.getPassword()))
                .build();
        applicationUserRepository.save(applicationUser);

        //Saving Organization
        Organization organization = saveOrganization(registrationRequestDTO, applicationUser);
        organizationRepository.save(organization);


        if (registrationRequestDTO.getOrganizationName() == null && registrationRequestDTO.getOrganizationPanNo() == null) {
            Role role = Role.builder()
                    .roleType("USER")
                    .applicationUser(applicationUser)
                    .build();
            roleRepository.save(role);
        }

        if (registrationRequestDTO.getOrganizationName() != null && registrationRequestDTO.getOrganizationPanNo() != null) {
            Role role = Role.builder()
                    .roleType("ADMIN")
                    .applicationUser(applicationUser)
                    .build();
            roleRepository.save(role);
        }


        final String token = UUID.randomUUID().toString();
        ConfirmationRegistrationToken confirmationRegistrationToken =
                ConfirmationRegistrationToken.builder()
                        .registrationToken(token)
                        .createdAt(ZonedDateTime.now())
                        .expiresAt(ZonedDateTime.now().plusMinutes(15))
                        .applicationUser(applicationUser)
                        .build();
        confirmationTokenService.savaRegistrationToken(confirmationRegistrationToken);
        EmailFieldDTO emailFieldDTO = EmailMapper.mapperToSendEmail(registrationRequestDTO, token);
        var emailDTO = EmailConverter.sendEmailToRegister(emailFieldDTO);
        emailSenderImpl.sendEmail(emailDTO);
    }


    private Organization saveOrganization(RegistrationRequestDTO registrationRequestDTO, ApplicationUser applicationUser) {
        return Organization.builder()
                .organizationName(registrationRequestDTO.getOrganizationName() == null ? "null" : registrationRequestDTO.getOrganizationName())
                .organizationPanNo(registrationRequestDTO.getOrganizationPanNo() == null ? "null" : registrationRequestDTO.getOrganizationPanNo())
                .isActive(registrationRequestDTO.getOrganizationName() == null || registrationRequestDTO.getOrganizationPanNo() == null ? false : true)
                .applicationUser(Collections.singletonList(applicationUser))
                .build();

    }
}
