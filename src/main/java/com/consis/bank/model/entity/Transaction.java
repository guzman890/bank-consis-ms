package com.consis.bank.model.entity;

import com.consis.bank.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "transaction_date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private double initialBalance;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long customerId;
}
