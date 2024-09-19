package com.consis.bank.service;

import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.TransactionType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    List<TransactionDTO> findAllByAccountIdAndTransactionTypeAndDate(Long accountId, TransactionType transactionType, LocalDate date);
    List<TransactionDTO> findAllByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate);
    TransactionDTO save(TransactionDTO transaction);
    TransactionDTO update(TransactionDTO transaction);
    TransactionDTO findById(Long id);
    List<TransactionDTO> findAll();
    void deleteById(Long id);
}