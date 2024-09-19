package com.consis.bank.business;

import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.model.dto.CustomerDTO;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.*;
import com.consis.bank.service.AccountService;
import com.consis.bank.service.CustomerService;
import com.consis.bank.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransactionExecuteServiceIntegrationTest {

    @Autowired
    private TransactionExecuteService transactionExecuteService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    private AccountDTO accountDTO;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {

        customerDTO = new CustomerDTO();
        customerDTO.setName("John Doe");
        customerDTO.setGender(Gender.MALE);
        customerDTO.setAge(30);
        customerDTO.setIdentification("ID123456");
        customerDTO.setAddress("123 Main St");
        customerDTO.setPhone("1234567890");
        customerDTO.setPassword("password");
        customerDTO.setStatus(CustomerStatus.ACTIVE);
        customerDTO = customerService.save(customerDTO);


        accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("12345");
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setStatus(AccountStatus.ACTIVE);
        accountDTO.setInitialBalance(1000.0);
        accountDTO.setCustomerId(1L);
        accountDTO = accountService.save(accountDTO);
    }

    @Test
    void testSaveTransaction_Credit() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountId(accountDTO.getId());
        transactionDTO.setCustomerId(customerDTO.getId());
        transactionDTO.setTransactionType(TransactionType.CREDIT);
        transactionDTO.setAmount(100.0);
        transactionDTO.setDate(LocalDate.now());

        TransactionDTO result = transactionExecuteService.saveTransaction(transactionDTO);

        assertNotNull(result);
        assertEquals(1100.0, result.getBalance());
    }
}