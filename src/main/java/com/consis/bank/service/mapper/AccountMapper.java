package com.consis.bank.service.mapper;

import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.model.entity.Account;

public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setAccountType(account.getAccountType());
        dto.setInitialBalance(account.getInitialBalance());
        dto.setStatus(account.getStatus());
        dto.setCustomerId(account.getCustomerId());
        return dto;
    }

    public static Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setAccountNumber(dto.getAccountNumber());
        account.setAccountType(dto.getAccountType());
        account.setInitialBalance(dto.getInitialBalance());
        account.setStatus(dto.getStatus());
        account.setCustomerId(dto.getCustomerId());
        return account;
    }
}