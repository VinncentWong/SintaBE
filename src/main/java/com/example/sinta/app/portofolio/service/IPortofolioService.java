package com.example.sinta.app.portofolio.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.sinta.dto.PortofolioDto;

public interface IPortofolioService {
    ResponseEntity<Map<String, Object>> create(Long id, PortofolioDto dto);
    ResponseEntity<Map<String, Object>> get(Long id);
}
