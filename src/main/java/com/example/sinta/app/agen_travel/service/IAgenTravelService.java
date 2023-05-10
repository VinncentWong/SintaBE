package com.example.sinta.app.agen_travel.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.sinta.dto.AgenTravelDto;
import com.example.sinta.exception.AgenTravelAlreadyExistException;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.WrongCredentialException;

public interface IAgenTravelService{
    ResponseEntity<Map<String, Object>> createAgenTravel(AgenTravelDto.Register dto) throws AgenTravelAlreadyExistException;
    ResponseEntity<Map<String, Object>> loginAgenTravel(AgenTravelDto.Login dto) throws AgenTravelNotExistException, WrongCredentialException;
    ResponseEntity<Map<String, Object>> updateVerifikasiAgenTravel(Long id);
    ResponseEntity<Map<String, Object>> lengkapiProfilAgenTravel(Long id, AgenTravelDto.LengkapiProfil dto, MultipartFile file) throws AgenTravelNotExistException, IOException;
}
