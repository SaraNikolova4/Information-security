package com.example.lab2ib.exception;

public class NoPassword extends RuntimeException{
    public NoPassword() {
        super("Vnesete password");
    }
}
