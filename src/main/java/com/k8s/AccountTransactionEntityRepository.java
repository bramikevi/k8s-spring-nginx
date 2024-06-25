package com.k8s;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionEntityRepository extends JpaRepository<AccountTransactionEntity, Long> {
}