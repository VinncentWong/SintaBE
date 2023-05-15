package com.example.sinta.app.pemesanan_luar_negeri.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PemesananDalamNegeriDto;
import com.example.sinta.dto.PemesananLuarNegeriDto;

public interface IPemesananLuarNegeriService {
    ResponseEntity<Map<String, Object>> createPemesanan(Long userId, Long agenTravelId, Long paketWisataId, PemesananLuarNegeriDto.Create dto);
    ResponseEntity<Map<String, Object>> getPemesanan(Long id);
}
