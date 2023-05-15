package com.example.sinta.app.paket_wisata.controller.impl;

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

import com.example.sinta.app.paket_wisata.controller.IPaketWisataController;
import com.example.sinta.app.paket_wisata.service.IPaketWisataService;
import com.example.sinta.app.paket_wisata.service.impl.PaketWisataService;
import com.example.sinta.dto.PaketWisataDto.CreateOrUpdate;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paketwisata")
public class PaketWisataController implements IPaketWisataController{

    private final IPaketWisataService service;

    public PaketWisataController(PaketWisataService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createPaketWisata(JwtAuthentication jwtAuth, @RequestBody @Valid CreateOrUpdate dto)
            throws AgenTravelNotExistException {
        Long id = jwtAuth.getData().getId();
        return this.service.createPaketWisata(id, dto);
    }

    @GetMapping(
        value = "/get/agentravel/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisataByAgenTravelId(@PathVariable("id") Long id) {
        return this.service.getPaketWisataByAgenTravelId(id);
    }

    @GetMapping(
        value = "/get/paketwisata/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisataByPaketWisataId(@PathVariable("id") Long id) {
        return this.service.getPaketWisataByPaketWisataId(id);
    }

    @GetMapping(
        value = "/get",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisatas() {
        return this.service.getPaketWisatas();
    }

    @DeleteMapping(
        value = "/delete/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> deletePaketWisata(@PathVariable("id") Long id) {
        return this.service.deletePaketWisata(id);
    }

    @PatchMapping(
        value = "/update/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> updatePaketWisata(@PathVariable("id") Long id, @RequestBody @Valid CreateOrUpdate dto) {
        return this.service.updatePaketWisata(id, dto);
    }
    
}
