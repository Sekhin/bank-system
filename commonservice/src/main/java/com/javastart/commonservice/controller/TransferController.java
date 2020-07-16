package com.javastart.commonservice.controller;

import com.javastart.commonservice.controller.dto.transfer.TransferRequestDTO;
import com.javastart.commonservice.controller.dto.transfer.TransferResponseDTO;
import com.javastart.commonservice.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public TransferResponseDTO transfer(@RequestBody @Valid TransferRequestDTO transferRequestDTO) {
        return transferService.makeTransfer(transferRequestDTO.getAccountFrom(), transferRequestDTO.getAccountTo(),
                transferRequestDTO.getAmount());
    }
}
