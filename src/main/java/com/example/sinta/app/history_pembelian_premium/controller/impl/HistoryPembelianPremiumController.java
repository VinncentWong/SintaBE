package com.example.sinta.app.history_pembelian_premium.controller.impl;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinta.app.history_pembelian_premium.controller.IHistoryPembelianPremiumController;
import com.example.sinta.app.history_pembelian_premium.service.IHistoryPembelianPremiumService;
import com.example.sinta.dto.HistoryPembelianPremiumDto.Create;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.HistoryPembelianPremiumNotFoundException;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/premium")
public class HistoryPembelianPremiumController implements IHistoryPembelianPremiumController{
    
    private final IHistoryPembelianPremiumService service;

    public HistoryPembelianPremiumController(IHistoryPembelianPremiumService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createHistoryPembelian(JwtAuthentication jwtAuth, @RequestBody @Valid Create dto)
            throws AgenTravelNotExistException {
        Long id = jwtAuth.getData().getId();
        return this.service.createHistoryPembelian(id, dto);
    }

    @GetMapping(
        value = "/get/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getHistoryPembelian(@PathVariable("id") Long id) throws AgenTravelNotExistException, HistoryPembelianPremiumNotFoundException {
        return this.service.getHistoryPembelian(id);
    }

    
}
