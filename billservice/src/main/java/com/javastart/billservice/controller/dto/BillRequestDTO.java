package com.javastart.billservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {

    private BigDecimal amount;

    private Boolean isOverdraftEnabled;

    private Long accountId;
}
