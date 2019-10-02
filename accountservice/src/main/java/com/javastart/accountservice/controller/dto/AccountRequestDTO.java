package com.javastart.accountservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {
    private String name;
    private String phone;
    private String mail;
}
