package com.k8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public BigDecimal getAccountBalance() {
        return accountService.getAccountBalance(1l);
    }

    @PostMapping("/zero")
    public void zeroOutAccountBalance() {
        accountService.zeroOutAccountBalance(1l);
    }

    @GetMapping("/random")
    public void processRandomTransaction() {
        Random random = new Random();
        BigDecimal amount = BigDecimal.valueOf(random.nextInt(10) + 1); // Losowa kwota od 1 do 10
        TransactionType transactionType = random.nextBoolean() ? TransactionType.DEPOSIT : TransactionType.WITHDRAW; // Losowy typ transakcji

        TransactionDto transactionDto = new TransactionDto(1l, amount, transactionType);
        accountService.processTransaction(transactionDto);
    }

//    @GetMapping("/{accountId}/balance")
//    public BigDecimal getAccountBalance(@PathVariable Long accountId) {
//        return accountService.getAccountBalance(accountId);
//    }
//
//    @PostMapping("/{accountId}/zero-balance")
//    public void zeroOutAccountBalance(@PathVariable Long accountId) {
//        accountService.zeroOutAccountBalance(accountId);
//    }
//
//    @GetMapping("/{accountId}/random-transaction")
//    public void processRandomTransaction(@PathVariable Long accountId) {
//        Random random = new Random();
//        BigDecimal amount = BigDecimal.valueOf(random.nextInt(100) + 1); // Losowa kwota od 1 do 100
//        TransactionType transactionType = random.nextBoolean() ? TransactionType.DEPOSIT : TransactionType.WITHDRAW; // Losowy typ transakcji
//
//        TransactionDto transactionDto = new TransactionDto(accountId, amount, transactionType);
//        accountService.processTransaction(transactionDto);
//    }
}