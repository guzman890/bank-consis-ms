package com.consis.bank.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountStatementDTO {
    private Long accountId;
    private String accountNumber;
    private double initialBalance;
    private double totalDebits;
    private double totalCredits;
    private List<TransactionDTO> transactions;
}
