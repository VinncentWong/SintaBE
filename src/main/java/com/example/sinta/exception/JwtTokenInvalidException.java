package com.example.sinta.exception;

public class JwtTokenInvalidException extends RuntimeException{
    
    public JwtTokenInvalidException(String message){
        super(message);
    }
}
