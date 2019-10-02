package com.javastart.commonservice.controller;

import com.javastart.commonservice.controller.dto.CustomerRequestDTO;
import com.javastart.commonservice.controller.dto.CustomerResponseDTO;
import com.javastart.commonservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public String createCustomer(@RequestBody CustomerRequestDTO customerDTO) {
        return customerService.saveCustomer(customerDTO.getName(), customerDTO.getPhone(), customerDTO.getMail(),
                customerDTO.getAmount(), customerDTO.getIsOverdraftEnabled());
    }

    @GetMapping("/customer")
    public CustomerResponseDTO getCustomerById(@RequestParam Long accountId,
                                               @RequestParam Long billId) {
        return customerService.getCustomerById(accountId, billId);
    }
}
