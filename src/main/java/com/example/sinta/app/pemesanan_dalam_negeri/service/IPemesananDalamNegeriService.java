package com.example.sinta.app.pemesanan_dalam_negeri.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PemesananDalamNegeriDto;

public interface IPemesananDalamNegeriService{
    ResponseEntity<Map<String, Object>> createPemesanan(Long userId, Long agenTravelId, Long paketWisataId, PemesananDalamNegeriDto.Create dto);
    ResponseEntity<Map<String, Object>> getPemesanan(Long id);
    ResponseEntity<Map<String, Object>> getPemesananByUserId(Long id);
}
