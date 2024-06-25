package com.k8s;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class TransactionDto {
    Long accountId;
    BigDecimal amount;
    TransactionType transactionType;
}