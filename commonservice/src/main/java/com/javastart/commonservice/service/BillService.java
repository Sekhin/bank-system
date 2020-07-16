package com.javastart.commonservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.commonservice.controller.dto.BillRequestDTO;
import com.javastart.commonservice.controller.dto.BillResponseDTO;
import com.javastart.commonservice.exception.CommonServiceException;
import com.javastart.commonservice.http.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class BillService {

    @Value("${bill.service.url}")
    private String billServiceUrl;

    private final ObjectMapper objectMapper;

    private final RestService restService;

    @Autowired
    public BillService(ObjectMapper objectMapper, RestService restService) {
        this.objectMapper = objectMapper;
        this.restService = restService;
    }

    public ResponseEntity<String> getBillResponse(BigDecimal amount, Boolean isOverdraftEnabled, Long accountId) {
        BillRequestDTO billRequestDTO = new BillRequestDTO(amount, isOverdraftEnabled, accountId);
        String billJson = null;
        try {
            billJson = objectMapper.writeValueAsString(billRequestDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return restService.postForEntity(billJson, billServiceUrl + "/bills");
    }

    private BillResponseDTO serializedBillDTO(ResponseEntity<String> responseEntity){
        String body = responseEntity.getBody();
        try {
            return objectMapper.readValue(body, BillResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonServiceException(e.getMessage());
        }
    }

    public BillResponseDTO getBill(Long billId) {
        return serializedBillDTO(restService.getForEntity(setBillUrlBuilder(billId).toString()));
    }

    public void deleteBill(Long billId) {
        restService.delete(setBillUrlBuilder(billId).toString());
    }

    private StringBuilder setBillUrlBuilder(Long billId) {
        StringBuilder billUrlBuilder = new StringBuilder(billServiceUrl);
        billUrlBuilder.append("/bills/");
        billUrlBuilder.append(billId);
        return billUrlBuilder;
    }
}
