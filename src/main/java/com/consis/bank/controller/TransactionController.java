package com.consis.bank.controller;

import com.consis.bank.business.TransactionExecuteService;
import com.consis.bank.model.dto.TransactionDTO;
import com.consis.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionExecuteService transactionExecuteService;

    @PostMapping
    public ResponseEntity<TransactionDTO> save(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionExecuteService.saveTransaction(transactionDTO));
    }

    @PutMapping
    public ResponseEntity<TransactionDTO> update(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.update(transactionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}