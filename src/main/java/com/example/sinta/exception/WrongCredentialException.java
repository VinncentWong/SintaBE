package com.example.sinta.exception;

public class WrongCredentialException extends Exception{
    public WrongCredentialException() {}

    public WrongCredentialException(String message) {
        super(message);
    }
}
