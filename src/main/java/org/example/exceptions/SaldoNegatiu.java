package org.example.exceptions;

public class SaldoNegatiu extends RuntimeException{

    public SaldoNegatiu(String message) {
        super(message);
    }
}
