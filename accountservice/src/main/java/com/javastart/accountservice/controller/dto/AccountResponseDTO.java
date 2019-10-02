package com.javastart.accountservice.controller.dto;

import com.javastart.accountservice.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private String name;
    private String phone;
    private String mail;

    public AccountResponseDTO(Account account){
       this.name = account.getName();
       this.phone = account.getPhone();
       this.mail = account.getMail();
    }
}
