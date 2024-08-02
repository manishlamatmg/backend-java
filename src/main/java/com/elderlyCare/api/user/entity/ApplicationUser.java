package com.elderlyCare.api.user.entity;

import com.elderlyCare.api.base.IdentityNumber;
import com.elderlyCare.api.role.entity.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApplicationUser extends IdentityNumber {
   private String firstName;
   private String lastName;
   private String password;
   private String email;
   private String citizenShip;
   private Boolean isActive;
   @OneToOne
   @JoinColumn
   private Role role;
}
