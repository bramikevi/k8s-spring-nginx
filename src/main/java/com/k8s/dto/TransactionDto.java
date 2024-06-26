package com.k8s.dto;

import com.k8s.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class TransactionDto {
    Long accountId;
    BigDecimal amount;
    TransactionType transactionType;
}