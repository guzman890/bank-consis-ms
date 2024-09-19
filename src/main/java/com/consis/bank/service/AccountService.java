package com.consis.bank.service;

import com.consis.bank.model.dto.AccountDTO;
import java.util.List;

public interface AccountService {
    AccountDTO save(AccountDTO account);
    AccountDTO update(AccountDTO account);
    AccountDTO findById(Long id);
    List<AccountDTO> findAll();
    List<AccountDTO> findByCustomerId(Long customerId);
    void deleteById(Long id);
}
