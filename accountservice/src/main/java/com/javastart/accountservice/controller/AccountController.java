package com.javastart.accountservice.controller;

import com.javastart.accountservice.controller.dto.AccountResponseDTO;
import com.javastart.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public Long saveAccount(@RequestBody AccountResponseDTO accountResponseDTO) {
        return accountService.saveAccount(accountResponseDTO.getName(),
                accountResponseDTO.getPhone(), accountResponseDTO.getMail());
    }

    @GetMapping("/accounts/{id}")
    public AccountResponseDTO getAccount(@PathVariable Long id) {
        return new AccountResponseDTO(accountService.getAccountById(id));
    }

    @GetMapping("/accounts")
    public List<AccountResponseDTO> getAccounts() {
        return accountService.getAccounts()
                .stream()
                .map(AccountResponseDTO::new)
                .collect(Collectors.toList());
    }
}
