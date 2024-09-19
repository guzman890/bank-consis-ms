package com.consis.bank.controller;

import com.consis.bank.business.ReportExecuteService;
import com.consis.bank.model.dto.AccountStatementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {
    @Autowired
    private ReportExecuteService reportExecuteService;

    @GetMapping
    public ResponseEntity<List<AccountStatementDTO>> getAccountStatement(
            @RequestParam Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ResponseEntity.ok(reportExecuteService.getAccountStatement(customerId, start, end));
    }
}
