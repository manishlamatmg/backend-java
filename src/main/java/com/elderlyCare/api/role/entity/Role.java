package com.elderlyCare.api.role.entity;

import com.elderlyCare.api.base.IdentityNumber;
import com.elderlyCare.api.user.entity.ApplicationUser;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Entity
public class Role extends IdentityNumber {

    private String roleType;
    private String description;
    @OneToOne
    @JoinColumn
    private ApplicationUser applicationUser;
}
