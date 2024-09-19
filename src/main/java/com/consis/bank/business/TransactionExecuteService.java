package com.consis.bank.business;

import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.TransactionType;
import com.consis.bank.service.AccountService;
import com.consis.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionExecuteService {

    private static final double DAILY_WITHDRAWAL_LIMIT = 1000.0;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        AccountDTO account = validateAccount(transactionDTO.getAccountId());
        validateBalance(account, transactionDTO);
        validateDailyWithdrawalLimit(transactionDTO.getAccountId(), transactionDTO);

        transactionDTO.setInitialBalance(account.getInitialBalance());

        if (TransactionType.DEBIT.equals(transactionDTO.getTransactionType())) {
            account.setInitialBalance(account.getInitialBalance() - transactionDTO.getAmount());
        } else if (TransactionType.CREDIT.equals(transactionDTO.getTransactionType())) {
            account.setInitialBalance(account.getInitialBalance() + transactionDTO.getAmount());
        }

        transactionDTO.setBalance(account.getInitialBalance());

        accountService.save(account);

        return transactionService.save(transactionDTO);
    }

    public AccountDTO validateAccount(Long accountId) {
        return accountService.findById(accountId);
    }

    public void validateBalance(AccountDTO account, TransactionDTO transactionDTO) {
        if (TransactionType.DEBIT.equals(transactionDTO.getTransactionType()) && account.getInitialBalance() == 0) {
            throw new IllegalArgumentException("Saldo no disponible");
        }
    }

    public void validateDailyWithdrawalLimit(Long accountId, TransactionDTO transactionDTO) {
        double totalDebitsToday = transactionService.findAllByAccountIdAndTransactionTypeAndDate(
                        accountId, TransactionType.DEBIT, LocalDate.now())
                .stream()
                .mapToDouble(TransactionDTO::getAmount)
                .sum();

        if (TransactionType.DEBIT.equals(transactionDTO.getTransactionType()) &&
                (totalDebitsToday + transactionDTO.getAmount() > DAILY_WITHDRAWAL_LIMIT)) {
            throw new IllegalArgumentException("Límite diario de retiro excedido");
        }
    }
}
