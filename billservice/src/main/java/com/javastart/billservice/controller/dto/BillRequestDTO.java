package com.javastart.billservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("50000.00")
    private BigDecimal amount;

    @NotNull
    private Boolean isOverdraftEnabled;

    @NotNull
    private Long accountId;
}
