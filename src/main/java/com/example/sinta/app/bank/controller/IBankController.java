package com.example.sinta.app.bank.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.BankDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IBankController {
    ResponseEntity<Map<String, Object>> createBank(JwtAuthentication jwtAuth, BankDto.CreateOrUpdate dto) throws AgenTravelNotExistException;
    ResponseEntity<Map<String, Object>> getBank(Long id);
    ResponseEntity<Map<String, Object>> updateBank(Long idBank, BankDto.CreateOrUpdate dto);
    ResponseEntity<Map<String, Object>> deleteBank(Long id);
}
