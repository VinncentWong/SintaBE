package com.example.sinta.app.agen_travel.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.sinta.dto.AgenTravelDto;
import com.example.sinta.exception.AgenTravelAlreadyExistException;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.security.authentication.JwtAuthentication;

public interface IAgenTravelController {
    ResponseEntity<Map<String, Object>> createAgenTravel(AgenTravelDto.Register dto) throws AgenTravelAlreadyExistException;
    ResponseEntity<Map<String, Object>> loginAgenTravel(AgenTravelDto.Login dto) throws AgenTravelNotExistException, WrongCredentialException;
    ResponseEntity<Map<String, Object>> updateVerifikasiAgenTravel(Long id);
    ResponseEntity<Map<String, Object>> lengkapiProfilAgenTravel(JwtAuthentication jwtAuth, AgenTravelDto.LengkapiProfil dto, MultipartFile file) throws AgenTravelNotExistException, IOException;
}
