package com.javastart.billservice.service;

import com.javastart.billservice.entity.Bill;
import com.javastart.billservice.exception.BillNotFoundException;
import com.javastart.billservice.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBillById(Long billId) {
        return billRepository.findById(billId).orElseThrow(() -> new
                BillNotFoundException("Can't find bill with id" + billId));
    }

    public Long saveBill(BigDecimal amount, Boolean isOverdraftEnabled, Long accountId) {
        Bill bill = new Bill(amount, isOverdraftEnabled, accountId);
        return billRepository.save(bill).getId();
    }

    public Bill getBillByAccountId(Long accountId){
        return billRepository.findBillByAccountId(accountId)
                .orElseThrow(() -> new BillNotFoundException("Can't find bill with account id: " + accountId));

    }

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Transactional
    public void deleteBillById(Long billId) {
        billRepository.deleteBillById(billId);
    }

    @Transactional
    public String updateBill(BigDecimal amount, Boolean isOverdraftEnabled, Long accountId) {
        billRepository.updateBillByAccountId(amount, isOverdraftEnabled, accountId);
        return "Success";
    }
}
