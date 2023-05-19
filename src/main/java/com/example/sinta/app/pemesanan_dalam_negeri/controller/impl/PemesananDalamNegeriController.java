package com.example.sinta.app.pemesanan_dalam_negeri.controller.impl;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinta.app.pemesanan_dalam_negeri.controller.IPemesananDalamNegeriController;
import com.example.sinta.app.pemesanan_dalam_negeri.service.IPemesananDalamNegeriService;
import com.example.sinta.app.pemesanan_dalam_negeri.service.impl.PemesananDalamNegeriService;
import com.example.sinta.dto.PemesananDalamNegeriDto.Create;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pemesanan/dalamnegeri")
@Slf4j
public class PemesananDalamNegeriController implements IPemesananDalamNegeriController{

    private final IPemesananDalamNegeriService service;

    public PemesananDalamNegeriController(PemesananDalamNegeriService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createPemesanan(JwtAuthentication jwtAuth, @RequestParam("agenTravelId") Long agenTravelId, @RequestParam("paketWisataId") Long paketWisataId,
            @RequestBody @Valid Create dto) {
        Long userId = jwtAuth.getData().getId();
        return this.service.createPemesanan(userId, agenTravelId, paketWisataId, dto);
    }

    @GetMapping(
        value = "/get",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getPemesanan(JwtAuthentication jwtAuth) {
        Long id = jwtAuth.getData().getId();
        return this.service.getPemesanan(id);
    }

    @GetMapping(
        value = "/get/by/user",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getPemesananByUserId(JwtAuthentication jwtAuth) {
        Long id = jwtAuth.getData().getId();
        log.info("id = " + id);
        return this.service.getPemesananByUserId(id);
    }
    
}
