package com.javastart.commonservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotNull
    private String name;

    @NotNull
    @Length(min = 11, max = 12)
    private String phone;

    @NotNull
    private String mail;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("50000.00")
    private BigDecimal amount;

    @NotNull
    private Boolean isOverdraftEnabled;
}
