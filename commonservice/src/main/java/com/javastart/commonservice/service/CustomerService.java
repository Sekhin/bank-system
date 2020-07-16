package com.javastart.commonservice.service;

import com.javastart.commonservice.controller.dto.AccountDTO;
import com.javastart.commonservice.controller.dto.BillResponseDTO;
import com.javastart.commonservice.controller.dto.CustomerResponseDTO;
import com.javastart.commonservice.exception.BillServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    private final BillService billService;

    private final AccountService accountService;

    @Autowired
    public CustomerService(BillService billService,
                           AccountService accountService) {
        this.billService = billService;
        this.accountService = accountService;
    }

    public String saveCustomer(String name, String phone, String mail, BigDecimal amount, Boolean isOverdraftEnabled) {
        ResponseEntity<String> accountResponse = accountService.getAccountResponse(name, phone, mail);
        Optional<String> accountBody = Optional.of(accountResponse).map(ResponseEntity::getBody);
        String accountResponseString = accountBody
                .orElseThrow(() -> new BillServiceException("Can't save bill without account id"));
        ResponseEntity<String> billResponse = billService.getBillResponse(amount, isOverdraftEnabled, Long.
                valueOf(accountResponseString));
        StringBuilder sb = new StringBuilder(accountResponseString);
        sb.append(billResponse.getBody());
        return sb.toString();
    }

    public CustomerResponseDTO getCustomerById(Long accountId, Long billId) {
        AccountDTO accountResponse = accountService.getAccount(accountId);
        BillResponseDTO billResponse = billService.getBill(billId);

        return new CustomerResponseDTO(billResponse, accountResponse);
    }

    public void delete(Long accountId, Long billId) {
        accountService.deleteAccount(accountId);
        billService.deleteBill(billId);
    }
}
