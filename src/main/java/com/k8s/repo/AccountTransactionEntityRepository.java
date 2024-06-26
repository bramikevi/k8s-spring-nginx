package com.k8s.repo;

import com.k8s.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionEntityRepository extends JpaRepository<AccountTransactionEntity, Long> {
}