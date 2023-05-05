package com.example.sinta.app.user.controller;

import com.example.sinta.dto.UserDto;
import com.example.sinta.exception.UserAlreadyExistException;
import com.example.sinta.exception.UserNotFoundException;
import com.example.sinta.exception.WrongCredentialException;
import org.springframework.http.ResponseEntity;

import java.util.Map;
public interface IUserController {
    ResponseEntity<Map<String, Object>> createUser(UserDto.Create dto) throws UserAlreadyExistException;
    ResponseEntity<Map<String, Object>> loginUser(UserDto.Login dto) throws WrongCredentialException;
    ResponseEntity<Map<String, Object>> updateVerifikasiUser(Long id);
    ResponseEntity<Map<String, Object>> getUser(Long id) throws UserNotFoundException;
    ResponseEntity<Map<String, Object>> updateUser(Long id, UserDto.Update dto) throws UserNotFoundException, UserAlreadyExistException; 
}
