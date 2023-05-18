package com.example.sinta.app.paket_wisata.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.sinta.dto.PaketWisataDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IPaketWisataController {
    ResponseEntity<Map<String, Object>> createPaketWisata(JwtAuthentication jwtAuth, PaketWisataDto.CreateOrUpdate dto, MultipartFile file) throws AgenTravelNotExistException, IOException;
    ResponseEntity<Map<String, Object>> getPaketWisataByAgenTravelId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisataByPaketWisataId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisatas();
    ResponseEntity<Map<String, Object>> deletePaketWisata(Long id);
    ResponseEntity<Map<String, Object>> updatePaketWisata(Long id, PaketWisataDto.CreateOrUpdate dto);
    ResponseEntity<Map<String, Object>> getPaketWisataByDomain(String domain);
    ResponseEntity<Map<String, Object>> getPaketWisataByJenisKelengkapan(String jenisKelengkapan);
}
