package com.javastart.billservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private Boolean isOverdraftEnabled;

    private Long accountId;

    public Bill(BigDecimal amount, Boolean isOverdraftEnabled, Long accountId) {
        this.amount = amount;
        this.isOverdraftEnabled = isOverdraftEnabled;
        this.accountId = accountId;
    }
}
