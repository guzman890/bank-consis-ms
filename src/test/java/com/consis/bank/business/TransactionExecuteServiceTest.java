package com.consis.bank.business;

import static org.junit.jupiter.api.Assertions.*;

import com.consis.bank.exception.ExcedLimit;
import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.AccountStatus;
import com.consis.bank.model.enums.AccountType;
import com.consis.bank.model.enums.TransactionType;
import com.consis.bank.service.AccountService;
import com.consis.bank.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;

class TransactionExecuteServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionExecuteService transactionExecuteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTransaction_Debit() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAccountId(1L);
        transactionDTO.setTransactionType(TransactionType.DEBIT);
        transactionDTO.setAmount(100.0);
        transactionDTO.setDate(LocalDate.now());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setCustomerId(1L);
        accountDTO.setInitialBalance(200.0);

        when(accountService.findById(1L)).thenReturn(accountDTO);
        when(transactionService.findAllByAccountIdAndTransactionTypeAndDate(1L, TransactionType.DEBIT, LocalDate.now()))
                .thenReturn(Collections.emptyList());
        when(transactionService.save(any(TransactionDTO.class))).thenReturn(transactionDTO);

        TransactionDTO result = transactionExecuteService.saveTransaction(transactionDTO);

        assertNotNull(result);
        assertEquals(200.0, result.getInitialBalance());
        assertEquals(100.0, result.getBalance());
        verify(accountService, times(1)).save(any(AccountDTO.class));
        verify(transactionService, times(1)).save(any(TransactionDTO.class));
    }

    @Test
    void testSaveTransaction_Credit() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAccountId(1L);
        transactionDTO.setTransactionType(TransactionType.CREDIT);
        transactionDTO.setAmount(100.0);
        transactionDTO.setDate(LocalDate.now());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setInitialBalance(200.0);

        when(accountService.findById(1L)).thenReturn(accountDTO);
        when(transactionService.findAllByAccountIdAndTransactionTypeAndDate(1L, TransactionType.DEBIT, LocalDate.now()))
                .thenReturn(Collections.emptyList());
        when(transactionService.save(any(TransactionDTO.class))).thenReturn(transactionDTO);

        TransactionDTO result = transactionExecuteService.saveTransaction(transactionDTO);

        assertNotNull(result);
        assertEquals(200.0, result.getInitialBalance());
        assertEquals(300.0, result.getBalance());
        verify(accountService, times(1)).save(any(AccountDTO.class));
        verify(transactionService, times(1)).save(any(TransactionDTO.class));
    }

    @Test
    void testValidateAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setInitialBalance(200.0);

        when(accountService.findById(1L)).thenReturn(accountDTO);

        AccountDTO result = transactionExecuteService.validateAccount(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("12345", result.getAccountNumber());
        assertEquals(1L, result.getCustomerId());
        assertEquals(200.0, result.getInitialBalance());
        verify(accountService, times(1)).findById(1L);
    }

    @Test
    void testValidateBalance_ThrowsException() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1L);
        accountDTO.setAccountNumber("12345");
        accountDTO.setCustomerId(1L);
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setInitialBalance(0.0);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAccountId(1L);
        transactionDTO.setTransactionType(TransactionType.DEBIT);
        transactionDTO.setAmount(100.0);
        transactionDTO.setDate(LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> transactionExecuteService.validateBalance(accountDTO, transactionDTO));
    }

    @Test
    void testValidateDailyWithdrawalLimit_ThrowsException() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(1L);
        transactionDTO.setAccountId(1L);
        transactionDTO.setTransactionType(TransactionType.DEBIT);
        transactionDTO.setAmount(1001.0);
        transactionDTO.setDate(LocalDate.now());

        when(transactionService.findAllByAccountIdAndTransactionTypeAndDate(1L, TransactionType.DEBIT, LocalDate.now()))
                .thenReturn(Collections.singletonList(transactionDTO));

        assertThrows(ExcedLimit.class, () -> transactionExecuteService.validateDailyWithdrawalLimit(1L, transactionDTO));
    }
}