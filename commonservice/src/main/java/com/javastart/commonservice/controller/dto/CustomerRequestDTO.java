package com.javastart.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
    private String name;
    private String phone;
    private String mail;
    private BigDecimal amount;
    private Boolean isOverdraftEnabled;
}
