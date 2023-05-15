package com.example.sinta.app.portofolio.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PortofolioDto;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IPortofolioController {
    ResponseEntity<Map<String, Object>> create(JwtAuthentication jwtAuth, PortofolioDto dto);
    ResponseEntity<Map<String, Object>> get(Long id);
}
