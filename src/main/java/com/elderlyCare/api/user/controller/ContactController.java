package com.elderlyCare.api.user.controller;

import com.elderlyCare.api.aluthProvider.UserProvider;
import com.elderlyCare.api.base.HttpResponsesMessage;
import com.elderlyCare.api.user.dto.ApplicationContactDT0;
import com.elderlyCare.api.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contacts")
public class ContactController {
    private final UserService userService;
    private final UserProvider userProvider;
    public ContactController (UserService userService, UserProvider userProvider){
        this.userService = userService;
        this.userProvider = userProvider;
    }

    @PostMapping()
    public HttpResponsesMessage updateUserInformation(@RequestBody ApplicationContactDT0 applicationContactDT0){
        return userService.saveContact(applicationContactDT0);
    }
}
