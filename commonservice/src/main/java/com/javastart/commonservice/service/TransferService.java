package com.javastart.commonservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.commonservice.controller.dto.BillRequestDTO;
import com.javastart.commonservice.controller.dto.BillResponseDTO;
import com.javastart.commonservice.controller.dto.transfer.TransferResponseDTO;
import com.javastart.commonservice.exception.NotEnoughMoneyException;
import com.javastart.commonservice.http.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;

@Service
public class TransferService {

    @Value("${bill.service.url}")
    private String billServiceUrl;

    private final RestService restService;

    private final ObjectMapper objectMapper;

    @Autowired
    public TransferService(RestService restService, ObjectMapper objectMapper) {
        this.restService = restService;
        this.objectMapper = objectMapper;
    }

    public TransferResponseDTO makeTransfer(Long accountFrom, Long accountTo, BigDecimal amount) {
        StringBuilder sbFrom = new StringBuilder(billServiceUrl);
        sbFrom.append("/bills_by_account")
                .append("/")
                .append(accountFrom);
        StringBuilder sbTo = new StringBuilder(billServiceUrl);
        sbTo.append("/bills_by_account")
                .append("/")
                .append(accountTo);
        ResponseEntity<String> billStringFrom = restService.getForEntity(sbFrom.toString());
        ResponseEntity<String> billStringTo = restService.getForEntity(sbTo.toString());
        try {
            BillResponseDTO billFrom = objectMapper.readValue(billStringFrom.getBody(), BillResponseDTO.class);
            BillResponseDTO billTo = objectMapper.readValue(billStringTo.getBody(), BillResponseDTO.class);

            if ((billFrom.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) < 0) &&
                    !billFrom.getIsOverdraftEnabled()) {
                throw new NotEnoughMoneyException("Can't proceed transfer, for account id: " + accountFrom +
                        " amount of bill: " + billFrom.getAmount() + "sum of transfer: " + amount);
            }
            BillRequestDTO changeBillFrom = new BillRequestDTO(billFrom.getAmount().subtract(amount),
                    billFrom.getIsOverdraftEnabled(), accountFrom);
            BillRequestDTO changeBillTo = new BillRequestDTO(billTo.getAmount().add(amount),
                    billTo.getIsOverdraftEnabled(), accountTo);
            restService.put(objectMapper.writeValueAsString(changeBillFrom), billServiceUrl + "/bills");
            restService.put(objectMapper.writeValueAsString(changeBillTo), billServiceUrl + "/bills");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TransferResponseDTO("Success", Collections.emptyList());
    }
}
