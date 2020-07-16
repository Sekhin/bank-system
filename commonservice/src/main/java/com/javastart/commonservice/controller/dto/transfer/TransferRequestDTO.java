package com.javastart.commonservice.controller.dto.transfer;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequestDTO {

    @NotNull
    private Long accountFrom;

    @NotNull
    private Long accountTo;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("50000.00")
    private BigDecimal amount;
}
