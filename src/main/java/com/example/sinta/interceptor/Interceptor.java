package com.example.sinta.interceptor;

import com.example.sinta.exception.UserAlreadyExistException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class Interceptor {

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleException(UserAlreadyExistException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false, null);
    }

    @ExceptionHandler(WrongCredentialException.class)
    public ResponseEntity<Map<String, Object>> handleException(WrongCredentialException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED, false, null);
    }
}
