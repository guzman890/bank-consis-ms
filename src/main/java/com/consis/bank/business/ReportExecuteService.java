package com.consis.bank.business;

import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.model.dto.AccountStatementDTO;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.TransactionType;
import com.consis.bank.service.AccountService;
import com.consis.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportExecuteService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public List<AccountStatementDTO>  getAccountStatement(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<AccountDTO> accounts = accountService.findByCustomerId(customerId);
        List<AccountStatementDTO> accountStatements = new ArrayList<>();
        for (AccountDTO account : accounts) {
            AccountStatementDTO accountStatementDTO = new AccountStatementDTO();

            List<TransactionDTO> transactions = transactionService.findAllByAccountIdAndDateBetween(
                    account.getId(), startDate, endDate);

            double totalDebits = transactions.stream()
                    .filter(t -> t.getTransactionType().equals(TransactionType.DEBIT))
                    .mapToDouble(TransactionDTO::getAmount)
                    .sum();

            double totalCredits = transactions.stream()
                    .filter(t -> t.getTransactionType().equals(TransactionType.CREDIT))
                    .mapToDouble(TransactionDTO::getAmount)
                    .sum();

            accountStatementDTO.setAccountId(account.getId());
            accountStatementDTO.setAccountNumber(account.getAccountNumber());
            accountStatementDTO.setInitialBalance(account.getInitialBalance());
            accountStatementDTO.setTotalDebits(totalDebits);
            accountStatementDTO.setTotalCredits(totalCredits);
            accountStatementDTO.setTransactions(new ArrayList<>(transactions));
            accountStatements.add(accountStatementDTO);
        }

        return accountStatements;
    }
}
