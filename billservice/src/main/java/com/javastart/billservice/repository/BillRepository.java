package com.javastart.billservice.repository;

import com.javastart.billservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findBillByAccountId(Long accountId);

    void deleteBillById(Long billId);

    @Modifying
    @Query(nativeQuery = true, value = "update bill set amount = ?1, is_overdraft_enabled = ?2 where account_id =?3")
    void updateBillByAccountId(BigDecimal amount, Boolean isOverdraftEnabled, Long accoountId);
}
