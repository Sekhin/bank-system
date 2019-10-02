package com.javastart.billservice.controller;

import com.javastart.billservice.controller.dto.BillRequestDTO;
import com.javastart.billservice.controller.dto.BillResponseDTO;
import com.javastart.billservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/bills")
    public Long createBill(@RequestBody BillRequestDTO billRequestDTO){
        return billService.saveBill(billRequestDTO.getAmount(), billRequestDTO.getIsOverdraftEnabled(),
                billRequestDTO.getAccountId());
    }

    @GetMapping("/bills/{billId}")
    public BillResponseDTO getBill(@PathVariable Long billId) {
        return new BillResponseDTO(billService.getBillById(billId));
    }

    @GetMapping("/bills_by_account/{accountId}")
    public BillResponseDTO getBillByAccountId(@PathVariable Long accountId) {
        return new BillResponseDTO(billService.getBillByAccountId(accountId));
    }

    @GetMapping("/bills")
    public List<BillResponseDTO> getBills(){
        return billService.getBills().stream()
                .map(BillResponseDTO::new)
                .collect(Collectors.toList());
    }
}
