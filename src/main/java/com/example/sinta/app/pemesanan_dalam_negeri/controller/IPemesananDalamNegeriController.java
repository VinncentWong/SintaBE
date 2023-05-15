package com.example.sinta.app.pemesanan_dalam_negeri.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PemesananDalamNegeriDto;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IPemesananDalamNegeriController {
    ResponseEntity<Map<String, Object>> createPemesanan(JwtAuthentication jwtAuth, Long agenTravelId, Long paketWisataId, PemesananDalamNegeriDto.Create dto);
    ResponseEntity<Map<String, Object>> getPemesanan(JwtAuthentication jwtAuth);
}
