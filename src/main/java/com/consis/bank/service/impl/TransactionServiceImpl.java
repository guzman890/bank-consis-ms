package com.consis.bank.service.impl;

import com.consis.bank.exception.EntityNotFoundException;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.model.enums.TransactionType;
import com.consis.bank.service.mapper.TransactionMapper;
import com.consis.bank.repository.TransactionRepository;
import com.consis.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> findAllByAccountIdAndTransactionTypeAndDate(Long accountId, TransactionType transactionType, LocalDate date) {
        return transactionRepository.findAllByAccountIdAndTransactionTypeAndDate(accountId, transactionType, date).stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findAllByAccountIdAndDateBetween(Long accountId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findAllByAccountIdAndDateBetween(accountId, startDate, endDate).stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        return TransactionMapper.toDTO(transactionRepository.save(TransactionMapper.toEntity(transactionDTO)));
    }

    @Override
    public TransactionDTO update(TransactionDTO transactionDTO) {
        return transactionRepository.findById(transactionDTO.getId())
                .map(existingTransaction -> TransactionMapper.toEntity(transactionDTO))
                .map(transactionRepository::save)
                .map(TransactionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
    }

    @Override
    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id)
                .map(TransactionMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
    }

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}