package com.elderlyCare.api.organization.entity;

import com.elderlyCare.api.base.IdentityNumber;
import com.elderlyCare.api.user.entity.ApplicationUser;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Organization  extends IdentityNumber {

    private String organizationName;
    private String organizationPanNo;
    private boolean isActive;
    @OneToMany
    @JoinColumn
    private List<ApplicationUser> applicationUser;
}
