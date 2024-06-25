package com.k8s;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountEntityRepository accountEntityRepository;
    private final AccountTransactionEntityRepository accountTransactionEntityRepository;

    @Transactional
    public void processTransaction(final TransactionDto transactionDto) {
        Long accountId = transactionDto.getAccountId();
        BigDecimal amount = transactionDto.getAmount();
        TransactionType transactionType = transactionDto.getTransactionType();

        AccountEntity accountEntity = accountEntityRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        BigDecimal balanceBefore = accountEntity.getBalance();
        BigDecimal balanceAfter;

        if (transactionType == TransactionType.DEPOSIT) {
            balanceAfter = balanceBefore.add(amount);
        } else if (transactionType == TransactionType.WITHDRAW) {
            if (balanceBefore.compareTo(amount) < 0) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            balanceAfter = balanceBefore.subtract(amount);
        } else {
            throw new IllegalArgumentException("Unsupported transaction type");
        }

        accountEntity.setBalance(balanceAfter);
        accountEntityRepository.save(accountEntity);

        AccountTransactionEntity transaction = new AccountTransactionEntity();
        transaction.setAccountEntity(accountEntity);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setBalanceBefore(balanceBefore);
        transaction.setBalanceAfter(balanceAfter);
        transaction.setTransactionTime(LocalDateTime.now());

        accountTransactionEntityRepository.save(transaction);

        log.info("Processed {} transaction for account {} with amount {}",
                transactionType, accountId, amount);
    }

    @Transactional(readOnly = true)
    public BigDecimal getAccountBalance(Long accountId) {
        AccountEntity accountEntity = accountEntityRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        return accountEntity.getBalance();
    }

    @Transactional
    public void zeroOutAccountBalance(Long accountId) {
        AccountEntity accountEntity = accountEntityRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        BigDecimal balanceBefore = accountEntity.getBalance();
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntityRepository.save(accountEntity);

    }
}