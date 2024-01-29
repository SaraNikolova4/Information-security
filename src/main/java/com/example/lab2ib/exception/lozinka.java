package com.example.lab2ib.exception;

public class lozinka extends RuntimeException{
    public lozinka() {
        super("Lozinkata mora da sodrzi golema bukva, specijalen znak i brojka!");
    }
}
