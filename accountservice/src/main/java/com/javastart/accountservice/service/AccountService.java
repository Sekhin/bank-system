package com.javastart.accountservice.service;

import com.javastart.accountservice.entity.Account;
import com.javastart.accountservice.exception.AccountNotFoundException;
import com.javastart.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long saveAccount(String name, String phone, String mail){
        Account account = new Account(name, phone, mail);
        return accountRepository.save(account).getId();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("Can't find account with id: " + id));
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}
