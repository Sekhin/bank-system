package com.javastart.accountservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {

    @NotNull
    private String name;

    @NotNull
    @Length(min = 11, max = 11)
    private String phone;

    @NotNull
    private String mail;
}
