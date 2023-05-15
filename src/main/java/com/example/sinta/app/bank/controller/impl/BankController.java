package com.example.sinta.app.bank.controller.impl;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sinta.app.bank.controller.IBankController;
import com.example.sinta.app.bank.service.IBankService;
import com.example.sinta.app.bank.service.impl.BankService;
import com.example.sinta.dto.BankDto.CreateOrUpdate;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController implements IBankController{
    
    private final IBankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createBank(JwtAuthentication jwtAuth, @RequestBody @Valid CreateOrUpdate dto)
            throws AgenTravelNotExistException {
        Long id = jwtAuth.getData().getId();
        return this.service.createBank(id, dto);
    }

    @GetMapping(
        value = "/get/{idTraveler}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getBank(@PathVariable("idTraveler") Long id) {
        return this.service.getBank(id);
    }

    @PatchMapping(
        value = "/update/{idBank}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> updateBank(@PathVariable("idBank") Long idBank, @RequestBody @Valid CreateOrUpdate dto) {
        return this.service.updateBank(idBank, dto);
    }

    @DeleteMapping(
        value = "/delete/{idBank}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> deleteBank(@PathVariable("idBank") Long id) {
        return this.service.deleteBank(id);
    }

    
}
