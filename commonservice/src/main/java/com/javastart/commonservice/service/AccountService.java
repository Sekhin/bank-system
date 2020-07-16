package com.javastart.commonservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.commonservice.controller.dto.AccountDTO;
import com.javastart.commonservice.exception.CommonServiceException;
import com.javastart.commonservice.http.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountService {

    @Value("${account.service.url}")
    private String accountServiceUrl;

    private final ObjectMapper objectMapper;

    private final RestService restService;

    @Autowired
    public AccountService(ObjectMapper objectMapper, RestService restService) {
        this.objectMapper = objectMapper;
        this.restService = restService;
    }

    public ResponseEntity<String> getAccountResponse(String name, String phone, String mail) {
        AccountDTO accountDTO = new AccountDTO(name, phone, mail);
        String accountJson = null;
        try {
            accountJson = objectMapper.writeValueAsString(accountDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return restService.postForEntity(accountJson, accountServiceUrl + "/accounts");
    }

    private AccountDTO serializedAccountDTO(ResponseEntity<String> responseEntity) {
        String body = responseEntity.getBody();
        try {
            return objectMapper.readValue(body, AccountDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonServiceException(e.getMessage());
        }
    }

    public AccountDTO getAccount(Long accountId) {
        return serializedAccountDTO(restService.getForEntity(setAccountUrlBuilder(accountId).toString()));
    }

    public void deleteAccount(Long accountId) {
        restService.delete(setAccountUrlBuilder(accountId).toString());
    }

    private StringBuilder setAccountUrlBuilder(Long accountId) {
        StringBuilder accountUrlBuilder = new StringBuilder(accountServiceUrl);
        accountUrlBuilder.append("/accounts/");
        accountUrlBuilder.append(accountId);
        return accountUrlBuilder;
    }
}
