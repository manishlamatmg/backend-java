package com.elderlyCare.api.user.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSupervisor extends IdentityNumber {

    @ManyToOne
    @JoinColumn()
    private ApplicationUser applicationUser;
}
