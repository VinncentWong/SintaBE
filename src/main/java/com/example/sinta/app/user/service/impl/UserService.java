package com.example.sinta.app.user.service.impl;

import com.example.sinta.app.user.repository.UserRepository;
import com.example.sinta.app.user.service.IUserService;
import com.example.sinta.domain.User;
import com.example.sinta.domain.Verifikasi;
import com.example.sinta.dto.UserDto;
import com.example.sinta.dto.UserDto.Update;
import com.example.sinta.exception.UserAlreadyExistException;
import com.example.sinta.exception.UserNotFoundException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.mapper.UserMapper;
import com.example.sinta.util.BcryptUtil;
import com.example.sinta.util.JwtUtil;
import com.example.sinta.util.ResponseUtil;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository repository;

    private final JavaMailSender mailSender;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    @Value("${application.domain}")
    private String applicationDomain;

    @Autowired
    public UserService(UserRepository repository, JavaMailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }

    private void sendEmail(String to, boolean updateEmail, User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("AdminSinta");
        message.setTo(to);
        message.setSubject("Email Konfirmasi");
        if(updateEmail){
            String text = """
                Halo, pesan ini adalah pesan konfirmasi bahwa Anda telah melakukan
                update email. Silahkan melakukan konfirmasi ulang email Anda
                di link yang sudah disiapkan di bawah ini.
                
                Link        : %s/user/update/verifikasi/%d       
                """.formatted(this.applicationDomain, user.getId());
            message.setText(text);
        } else {
            String text = """
                Halo, terima kasih karena sudah melakukan registrasi di aplikasi SINTA.
                Berikut adalah link konfirmasi yang dapat Anda buka agar mengkonfirmasi bahwa
                akun yang Anda registrasikan adalah benar akun Anda.
                
                Link        : %s/user/update/verifikasi/%d       
                """.formatted(this.applicationDomain, user.getId());
            message.setText(text);
        }
        this.mailSender.send(message);
    }

    private boolean doesUserAlreadyExist(String type, String value){
        switch (type){
            case "email" -> {
                return this.repository.findUserByEmail(value).isPresent();
            }
            case "notelp" -> {
                return this.repository.findUserByNoTelp(value).isPresent();
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createUser(UserDto.Create dto) throws UserAlreadyExistException {
        boolean doesUserExistByEmail = this.doesUserAlreadyExist("email", dto.email());
        boolean doesUserExistByNoTelp = this.doesUserAlreadyExist("notelp", dto.email());
        if(doesUserExistByEmail ||doesUserExistByNoTelp){
            throw new UserAlreadyExistException("User sudah terdaftar di database");
        }
        User user = dto.toUser();
        this.repository.save(user);
        this.sendEmail(user.getEmail(), false, user);
        var map = new LinkedHashMap<String, Object>();
        map.put("user", user);
        return responseUtil.sendResponse("Sukses membuat user", HttpStatus.CREATED, true, map);
    }


    @Override
    public ResponseEntity<Map<String, Object>> loginUser(UserDto.Login dto) throws WrongCredentialException {
        var optUser = this.repository.findUserByEmail(dto.email());
        if(optUser.isEmpty()){
            throw new WrongCredentialException("Kredensial tidak valid");
        }
        var user = optUser.get();
        if(user.getVerified().name().equals("MENUNGGU") || user.getVerified().name().equals("TIDAK_TERVERIFIKASI")){
            throw new WrongCredentialException("Pastikan Anda sudah melakukan verifikasi Email");
        }
        if(BcryptUtil.INSTANCE.comparePassword(dto.password(), user.getPassword())){
            String jwtToken = JwtUtil.INSTANCE.generateJwtToken(user);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("user", user);
            map.put("jwt_token", jwtToken);
            return responseUtil.sendResponse("User sukses melakukan login", HttpStatus.OK, true, map);
        } else {
            throw new WrongCredentialException("User tidak terautentikasi");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateVerifikasiUser(Long id) {
        this.repository.updateVerifikasiUser(id, Verifikasi.TERVERIFIKASI.ordinal());
        return this.responseUtil.sendResponse("Sukses mengupdate status verifikasi user", HttpStatus.OK, true, null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getUser(Long id) throws UserNotFoundException {
        Optional<User> opt = this.repository.findById(id);
        if(opt.isEmpty()){
            throw new UserNotFoundException("Data user tidak ditemukan");
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", opt.get());
        return this.responseUtil.sendResponse("Sukses mendapatkan data user", HttpStatus.OK, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateUser(Long id, Update dto) throws UserNotFoundException, UserAlreadyExistException {
        Optional<User> opt = this.repository.findById(id);
        Optional<User> check = this.repository.findUserByEmail(dto.email());
        if(check.isPresent()){
            if(opt.isPresent()){
                final User checkUser = check.get();
                final User user = opt.get();
                if(!user.getId().equals(checkUser.getId())){
                    throw new UserAlreadyExistException("User sudah terdaftar di database");
                }
            } else {
                throw new UserNotFoundException("Data user tidak ditemukan");
            }
        }
        User user = opt.get();
        if(!user.getEmail().equals(dto.email())){
            user.setVerified(Verifikasi.MENUNGGU);
        }
        UserMapper.INSTANCE.updateUser(dto, user);
        this.repository.save(user);
        this.sendEmail(user.getEmail(), true, user);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", user);
        return this.responseUtil.sendResponse("Sukses mengupdate data user", HttpStatus.OK, true, map);
    }

}
