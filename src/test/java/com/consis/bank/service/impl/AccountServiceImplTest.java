package com.consis.bank.service.impl;

import com.consis.bank.exception.EntityNotFoundException;
import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.repository.AccountRepository;
import com.consis.bank.service.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialBalance(1000.0);

        when(accountRepository.save(any())).thenReturn(AccountMapper.toEntity(accountDTO));

        AccountDTO result = accountService.save(accountDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("12345", result.getAccountNumber());
        assertEquals(1L, result.getCustomerId());
        assertEquals(1000.0, result.getInitialBalance());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialBalance(1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(AccountMapper.toEntity(accountDTO)));
        when(accountRepository.save(any())).thenReturn(AccountMapper.toEntity(accountDTO));

        AccountDTO result = accountService.update(accountDTO);

        assertNotNull(result);
        assertEquals("12345", result.getAccountNumber());
        assertEquals(1L, result.getCustomerId());
        assertEquals(1000.0, result.getInitialBalance());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void testUpdate_EntityNotFoundException() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialBalance(1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.update(accountDTO));
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById() {
        Long accountId = 1L;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountId);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialBalance(1000.0);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(AccountMapper.toEntity(accountDTO)));

        AccountDTO result = accountService.findById(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.getId());
        assertEquals("12345", result.getAccountNumber());
        assertEquals(1L, result.getCustomerId());
        assertEquals(1000.0, result.getInitialBalance());
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void testFindById_EntityNotFoundException() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.findById(accountId));
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void testFindAll() {
        AccountDTO account1 = new AccountDTO();
        account1.setId(1L);
        account1.setAccountNumber("12345");
        account1.setCustomerId(1L);
        account1.setInitialBalance(1000.0);

        AccountDTO account2 = new AccountDTO();
        account2.setId(2L);
        account2.setAccountNumber("67890");
        account2.setCustomerId(2L);
        account2.setInitialBalance(2000.0);

        when(accountRepository.findAll()).thenReturn(Arrays.asList(AccountMapper.toEntity(account1), AccountMapper.toEntity(account2)));

        List<AccountDTO> result = accountService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("12345", result.get(0).getAccountNumber());
        assertEquals(1L, result.get(0).getCustomerId());
        assertEquals(1000.0, result.get(0).getInitialBalance());
        assertEquals(2L, result.get(1).getId());
        assertEquals("67890", result.get(1).getAccountNumber());
        assertEquals(2L, result.get(1).getCustomerId());
        assertEquals(2000.0, result.get(1).getInitialBalance());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testFindByCustomerId() {
        Long customerId = 1L;
        AccountDTO account1 = new AccountDTO();
        account1.setId(1L);
        account1.setAccountNumber("12345");
        account1.setCustomerId(customerId);
        account1.setInitialBalance(1000.0);

        AccountDTO account2 = new AccountDTO();
        account2.setId(2L);
        account2.setAccountNumber("67890");
        account2.setCustomerId(customerId);
        account2.setInitialBalance(2000.0);

        when(accountRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(AccountMapper.toEntity(account1), AccountMapper.toEntity(account2)));

        List<AccountDTO> result = accountService.findByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("12345", result.get(0).getAccountNumber());
        assertEquals(customerId, result.get(0).getCustomerId());
        assertEquals(1000.0, result.get(0).getInitialBalance());
        assertEquals(2L, result.get(1).getId());
        assertEquals("67890", result.get(1).getAccountNumber());
        assertEquals(customerId, result.get(1).getCustomerId());
        assertEquals(2000.0, result.get(1).getInitialBalance());
        verify(accountRepository, times(1)).findByCustomerId(customerId);
    }

    @Test
    void testDeleteById() {
        Long accountId = 1L;

        doNothing().when(accountRepository).deleteById(accountId);

        accountService.deleteById(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
    }
}