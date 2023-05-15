package com.example.sinta.app.portofolio.controller.impl;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinta.app.portofolio.controller.IPortofolioController;
import com.example.sinta.app.portofolio.service.IPortofolioService;
import com.example.sinta.app.portofolio.service.impl.PortofolioService;
import com.example.sinta.dto.PortofolioDto;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/portofolio")
public class PortofolioController implements IPortofolioController{
    
    private final IPortofolioService service;

    public PortofolioController(PortofolioService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> create(JwtAuthentication jwtAuth, @RequestBody @Valid PortofolioDto dto) {
        Long id = jwtAuth.getData().getId();
        return this.service.create(id, dto);
    }

    @GetMapping(
        value = "/get/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> get(@PathVariable("id") Long id) {
        return this.service.get(id);
    }
}
