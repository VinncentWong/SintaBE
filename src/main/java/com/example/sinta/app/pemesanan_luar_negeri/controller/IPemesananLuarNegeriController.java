package com.example.sinta.app.pemesanan_luar_negeri.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PemesananLuarNegeriDto;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IPemesananLuarNegeriController {
    ResponseEntity<Map<String, Object>> createPemesanan(JwtAuthentication jwtAuth, Long agenTravelId, Long paketWisataId, PemesananLuarNegeriDto.Create dto);
    ResponseEntity<Map<String, Object>> getPemesanan(JwtAuthentication jwtAuth);
}
