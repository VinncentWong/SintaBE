package com.example.sinta.app.pemesanan_luar_negeri.controller.impl;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinta.app.pemesanan_luar_negeri.controller.IPemesananLuarNegeriController;
import com.example.sinta.app.pemesanan_luar_negeri.service.IPemesananLuarNegeriService;
import com.example.sinta.app.pemesanan_luar_negeri.service.impl.PemesananLuarNegeriService;
import com.example.sinta.dto.PemesananLuarNegeriDto.Create;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pemesanan/luarnegeri")
public class PemesananLuarNegeri implements IPemesananLuarNegeriController{

    private final IPemesananLuarNegeriService service;

    public PemesananLuarNegeri(PemesananLuarNegeriService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createPemesanan(JwtAuthentication jwtAuth, @RequestParam("agenTravelId") Long agenTravelId,
    @RequestParam("paketWisataId") Long paketWisataId, @RequestBody @Valid Create dto) {
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
    
}
