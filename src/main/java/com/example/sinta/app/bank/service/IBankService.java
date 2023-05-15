package com.example.sinta.app.bank.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.BankDto;
import com.example.sinta.exception.AgenTravelNotExistException;

public interface IBankService {
    ResponseEntity<Map<String, Object>> createBank(Long id, BankDto.CreateOrUpdate dto) throws AgenTravelNotExistException;
    ResponseEntity<Map<String, Object>> getBank(Long id);
    ResponseEntity<Map<String, Object>> updateBank(Long id, BankDto.CreateOrUpdate dto);
    ResponseEntity<Map<String, Object>> deleteBank(Long id);
}
