package com.javastart.billservice.controller.dto;

import com.javastart.billservice.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BillResponseDTO {
    private BigDecimal amount;

    private Boolean isOverdraftEnabled;

    public BillResponseDTO(Bill bill) {
        this.amount = bill.getAmount();
        this.isOverdraftEnabled = bill.getIsOverdraftEnabled();
    }
}
