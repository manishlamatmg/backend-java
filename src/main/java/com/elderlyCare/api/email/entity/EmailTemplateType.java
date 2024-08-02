package com.elderlyCare.api.email.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailTemplateType extends IdentityNumber {
    private String name;
    private String description;
}
