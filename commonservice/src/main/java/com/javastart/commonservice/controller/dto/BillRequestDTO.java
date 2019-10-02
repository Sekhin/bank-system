package com.javastart.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class BillRequestDTO {
    private BigDecimal amount;
    private Boolean isOverdraftEnabled;
    private Long accountId;
}
