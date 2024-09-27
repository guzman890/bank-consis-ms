package com.consis.bank.exception;

public class ExcedLimit extends RuntimeException {
    public ExcedLimit(String message) {
        super(message);
    }
}