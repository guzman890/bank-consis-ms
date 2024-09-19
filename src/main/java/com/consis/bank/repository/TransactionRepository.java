package com.consis.bank.repository;

import com.consis.bank.model.entity.Transaction;
import com.consis.bank.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountIdAndTransactionTypeAndDate(Long accountId, TransactionType transactionType, LocalDate date);
    List<Transaction> findAllByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
}
