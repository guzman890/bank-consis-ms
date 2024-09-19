package com.consis.bank.service.impl;

import com.consis.bank.exception.EntityNotFoundException;
import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.service.mapper.AccountMapper;
import com.consis.bank.repository.AccountRepository;
import com.consis.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        return AccountMapper.toDTO(accountRepository.save(AccountMapper.toEntity(accountDTO)));
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        return accountRepository.findById(accountDTO.getId())
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(accountDTO.getAccountNumber());
                    existingAccount.setAccountType(accountDTO.getAccountType());
                    existingAccount.setInitialBalance(accountDTO.getInitialBalance());
                    existingAccount.setStatus(accountDTO.getStatus());
                    existingAccount.setCustomerId(accountDTO.getCustomerId());
                    return accountRepository.save(existingAccount);
                })
                .map(AccountMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    @Override
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}