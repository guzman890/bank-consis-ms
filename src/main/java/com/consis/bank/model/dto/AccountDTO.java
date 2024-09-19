package com.consis.bank.model.dto;

import com.consis.bank.model.enums.AccountStatus;
import com.consis.bank.model.enums.AccountType;
import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private double initialBalance;
    private AccountStatus status;
    private Long customerId;

}