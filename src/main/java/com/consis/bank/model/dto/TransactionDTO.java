package com.consis.bank.model.dto;

import com.consis.bank.model.enums.TransactionType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long id;
    private LocalDate date;
    private TransactionType transactionType;
    private double initialBalance;
    private double amount;
    private double balance;
    private Long accountId;
    private Long customerId;
}
