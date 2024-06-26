package com.k8s.controller;

import com.k8s.dto.TransactionDto;
import com.k8s.enums.TransactionType;
import com.k8s.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/balance")
    public BigDecimal getAccountBalance(@PathVariable Long accountId) {
        return accountService.getAccountBalance(accountId);
    }

    @PostMapping("/{accountId}/zero")
    public void zeroOutAccountBalance(@PathVariable Long accountId) {
        accountService.zeroOutAccountBalance(accountId);
    }

    @GetMapping("/{accountId}/random")
    public void processRandomTransaction(@PathVariable Long accountId) {
        Random random = new Random();
        BigDecimal amount = BigDecimal.valueOf(random.nextInt(10) + 1);
        TransactionType transactionType = random.nextBoolean() ? TransactionType.DEPOSIT : TransactionType.WITHDRAW;

        TransactionDto transactionDto = new TransactionDto(accountId, amount, transactionType);
        accountService.processTransaction(transactionDto);
    }
}