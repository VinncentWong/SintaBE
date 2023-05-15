package com.example.sinta.app.agen_travel.controller.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.sinta.app.agen_travel.controller.IAgenTravelController;
import com.example.sinta.app.agen_travel.service.IAgenTravelService;
import com.example.sinta.app.agen_travel.service.impl.AgenTravelService;
import com.example.sinta.dto.AgenTravelDto.LengkapiProfil;
import com.example.sinta.dto.AgenTravelDto.Login;
import com.example.sinta.dto.AgenTravelDto.Register;
import com.example.sinta.exception.AgenTravelAlreadyExistException;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.security.authentication.JwtAuthentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agentravel")
public class AgenTravelController implements IAgenTravelController{
    
    private final IAgenTravelService service;

    public AgenTravelController(AgenTravelService service) {
        this.service = service;
    }

    @PostMapping(
        value = "/create",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createAgenTravel(@RequestBody @Valid Register dto) throws AgenTravelAlreadyExistException {
        return this.service.createAgenTravel(dto);
    }

    @PostMapping(
        value = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> loginAgenTravel(@RequestBody @Valid Login dto)
            throws AgenTravelNotExistException, WrongCredentialException {
        return this.service.loginAgenTravel(dto);
    }

    @GetMapping(
        value = "/update/verifikasi/{agenTravelId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> updateVerifikasiAgenTravel(@PathVariable("agenTravelId") Long id) {
        return this.service.updateVerifikasiAgenTravel(id);
    }

    @PatchMapping(
        value = "/lengkapiprofil",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> lengkapiProfilAgenTravel(
        JwtAuthentication jwtAuth, 
        @Valid @RequestPart("dto") LengkapiProfil dto, 
        @RequestPart("suratIzinUsaha") MultipartFile file
    ) throws AgenTravelNotExistException, IOException {
        Long id = jwtAuth.getData().getId();
        return this.service.lengkapiProfilAgenTravel(id, dto, file);
    }

    @GetMapping(
        value = "/get/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getAgenTravel(@PathVariable("id") Long id) throws AgenTravelNotExistException {
        return this.service.getAgenTravel(id);
    }

    
}
