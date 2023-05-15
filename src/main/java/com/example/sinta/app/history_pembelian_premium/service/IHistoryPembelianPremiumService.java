package com.example.sinta.app.history_pembelian_premium.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.HistoryPembelianPremiumDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.HistoryPembelianPremiumNotFoundException;

public interface IHistoryPembelianPremiumService {
    ResponseEntity<Map<String, Object>> createHistoryPembelian(Long id, HistoryPembelianPremiumDto.Create dto) throws AgenTravelNotExistException;
    ResponseEntity<Map<String, Object>> getHistoryPembelian(Long id) throws AgenTravelNotExistException, HistoryPembelianPremiumNotFoundException;
}
