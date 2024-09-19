package com.consis.bank.controller;

import com.consis.bank.model.dto.AccountDTO;
import com.consis.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.save(accountDTO));
    }

    @PutMapping
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.update(accountDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}