package com.example.sinta.app.user.controller.impl;

import com.example.sinta.app.user.controller.IUserController;
import com.example.sinta.app.user.service.IUserService;
import com.example.sinta.app.user.service.impl.UserService;
import com.example.sinta.dto.UserDto;
import com.example.sinta.dto.UserDto.Update;
import com.example.sinta.exception.UserAlreadyExistException;
import com.example.sinta.exception.UserNotFoundException;
import com.example.sinta.exception.WrongCredentialException;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController implements IUserController {

    private final IUserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(
            value = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody @Valid UserDto.Create dto) throws UserAlreadyExistException {
        return this.service.createUser(dto);
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody @Valid UserDto.Login dto) throws WrongCredentialException {
        return this.service.loginUser(dto);
    }

    @GetMapping(
            value = "/update/verifikasi/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> updateVerifikasiUser(@PathVariable("id") Long id) {
        return this.service.updateVerifikasiUser(id);
    }

    @GetMapping(
        value = "/get/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return this.service.getUser(id);
    }

    @PatchMapping(
        value = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("id") Long id, @RequestBody Update dto) throws UserNotFoundException, UserAlreadyExistException {
        return this.service.updateUser(id, dto);
    }
}
