package com.elderlyCare.api.aluthProvider;

import com.elderlyCare.api.exception.ElderlyCareException;
import com.elderlyCare.api.security.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserProvider {

    public UserDetailsDTO userDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDetails ;
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            userDetails = (UserDTO) principal;
            if(userDetails.getUserId() == null){
                throw new ElderlyCareException("UserId not found", HttpStatus.BAD_REQUEST);
            }else
                return new UserDetailsDTO(userDetails.getUserId());
        } else
            throw new ElderlyCareException("User is not Created", HttpStatus.BAD_REQUEST);
    }
}
