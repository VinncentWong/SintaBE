package com.example.sinta.app.paket_wisata.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PaketWisataDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IPaketWisataController {
    ResponseEntity<Map<String, Object>> createPaketWisata(JwtAuthentication jwtAuth, PaketWisataDto.CreateOrUpdate dto) throws AgenTravelNotExistException;
    ResponseEntity<Map<String, Object>> getPaketWisataByAgenTravelId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisataByPaketWisataId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisatas();
    ResponseEntity<Map<String, Object>> deletePaketWisata(Long id);
    ResponseEntity<Map<String, Object>> updatePaketWisata(Long id, PaketWisataDto.CreateOrUpdate dto);
}
