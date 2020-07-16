package com.javastart.commonservice.controller.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TransferResponseDTO {
    private String status;
    private List<String> errors;
}
