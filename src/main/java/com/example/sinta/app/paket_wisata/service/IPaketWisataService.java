package com.example.sinta.app.paket_wisata.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.sinta.domain.DomainPaketWisata;
import com.example.sinta.domain.JenisKelengkapan;
import com.example.sinta.dto.PaketWisataDto;
import com.example.sinta.exception.AgenTravelNotExistException;

public interface IPaketWisataService {
    ResponseEntity<Map<String, Object>> createPaketWisata(Long id, PaketWisataDto.CreateOrUpdate dto, MultipartFile file) throws AgenTravelNotExistException, IOException;
    ResponseEntity<Map<String, Object>> getPaketWisataByAgenTravelId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisataByPaketWisataId(Long id);
    ResponseEntity<Map<String, Object>> getPaketWisatas();
    ResponseEntity<Map<String, Object>> deletePaketWisata(Long id);
    ResponseEntity<Map<String, Object>> updatePaketWisata(Long id, PaketWisataDto.CreateOrUpdate dto);
    ResponseEntity<Map<String, Object>> getPaketWisataByDomain(DomainPaketWisata domain);
    ResponseEntity<Map<String, Object>> getPaketWisataByJenisKelengkapan(JenisKelengkapan jenisKelengkapan);
}
