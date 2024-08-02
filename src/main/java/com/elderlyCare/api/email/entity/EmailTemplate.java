package com.elderlyCare.api.email.entity;

import com.elderlyCare.api.base.IdentityNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;


@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailTemplate extends IdentityNumber {

    private String ccAddress;
    private String bccAddress;
    private String content;
    private String fromAddress;
    private String template;
    private String subject;
    @OneToOne
    @JoinColumn
    private EmailTemplateType emailTemplateType;
}
