package com.consis.bank.service.mapper;

import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.entity.Transaction;

public class TransactionMapper {

    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setAmount(transaction.getAmount());
        dto.setInitialBalance(transaction.getInitialBalance());
        dto.setBalance(transaction.getBalance());
        dto.setAccountId(transaction.getAccountId());
        dto.setCustomerId(transaction.getCustomerId());
        return dto;
    }

    public static Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setDate(dto.getDate());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setAmount(dto.getAmount());
        transaction.setInitialBalance(dto.getInitialBalance());
        transaction.setBalance(dto.getBalance());
        transaction.setAccountId(dto.getAccountId());
        transaction.setCustomerId(dto.getCustomerId());
        return transaction;
    }
}