package com.example.sinta.app.history_pembelian_premium.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.HistoryPembelianPremiumDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.HistoryPembelianPremiumNotFoundException;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IHistoryPembelianPremiumController {
    ResponseEntity<Map<String, Object>> createHistoryPembelian(JwtAuthentication jwtAuth, HistoryPembelianPremiumDto.Create dto) throws AgenTravelNotExistException;
    ResponseEntity<Map<String, Object>> getHistoryPembelian(Long id) throws AgenTravelNotExistException, HistoryPembelianPremiumNotFoundException;
}
