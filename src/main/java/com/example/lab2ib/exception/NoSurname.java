package com.example.lab2ib.exception;

public class NoSurname extends RuntimeException{

    public NoSurname() {
        super("Vnesete prezime!");
    }
}
