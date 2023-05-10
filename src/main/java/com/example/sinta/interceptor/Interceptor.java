package com.example.sinta.interceptor;

import com.example.sinta.exception.AgenTravelAlreadyExistException;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.JwtTokenInvalidException;
import com.example.sinta.exception.UserAlreadyExistException;
import com.example.sinta.exception.UserNotFoundException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(UserNotFoundException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.NOT_FOUND, false, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleException(MethodArgumentNotValidException ex){
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> messages = new ArrayList<>();
        ex.getAllErrors().forEach((e) -> {
            messages.add(e.getDefaultMessage());
        });
        map.put("message", messages);
        map.put("success", false);
        map.put("data", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    @ExceptionHandler(AgenTravelAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handleException(AgenTravelAlreadyExistException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, false, null);
    }

    @ExceptionHandler(AgenTravelNotExistException.class)
    public ResponseEntity<Map<String, Object>> handleException(AgenTravelNotExistException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.NOT_FOUND, false, null);
    }

    @ExceptionHandler(JwtTokenInvalidException.class)
    public ResponseEntity<Map<String, Object>> handleException(JwtTokenInvalidException ex){
        return this.responseUtil.sendResponse(ex.getMessage(), HttpStatus.FORBIDDEN, false, null);
    }
}
