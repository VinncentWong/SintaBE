package com.example.sinta.app.agen_travel.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.agen_travel.service.IAgenTravelService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.Verifikasi;
import com.example.sinta.dto.AgenTravelDto.LengkapiProfil;
import com.example.sinta.dto.AgenTravelDto.Login;
import com.example.sinta.dto.AgenTravelDto.Register;
import com.example.sinta.exception.AgenTravelAlreadyExistException;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.WrongCredentialException;
import com.example.sinta.mapper.AgenTravelMapper;
import com.example.sinta.util.BcryptUtil;
import com.example.sinta.util.JwtUtil;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AgenTravelService implements IAgenTravelService{

    private final JavaMailSender mailSender;

    private final AgenTravelRepository repository;

    private final BcryptUtil bcryptUtil = BcryptUtil.INSTANCE;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    private final JwtUtil jwtUtil = JwtUtil.INSTANCE;

    private final Cloudinary cloudinary;

    @Value("${application.domain}")
    private String applicationDomain;

    @Autowired
    public AgenTravelService(JavaMailSender mailSender, AgenTravelRepository repository, Cloudinary cloudinary) {
        this.mailSender = mailSender;
        this.repository = repository;
        this.cloudinary = cloudinary;
    }

    private void sendEmail(String to, boolean updateEmail, AgenTravel agentravel){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("AdminSinta");
        message.setTo(to);
        message.setSubject("Email Konfirmasi");
        if(updateEmail){
            String text = """
                Halo, pesan ini adalah pesan konfirmasi bahwa Anda telah melakukan
                update email. Silahkan melakukan konfirmasi ulang email Anda
                di link yang sudah disiapkan di bawah ini.
                
                Link        : %s/agentravel/update/verifikasi/%d       
                """.formatted(this.applicationDomain, agentravel.getId());
            message.setText(text);
        } else {
            String text = """
                Halo, terima kasih karena sudah melakukan registrasi di aplikasi SINTA.
                Berikut adalah link konfirmasi yang dapat Anda buka agar mengkonfirmasi bahwa
                akun yang Anda registrasikan adalah benar akun Anda.
                
                Link        : %s/agentravel/update/verifikasi/%d       
                """.formatted(this.applicationDomain, agentravel.getId());
            message.setText(text);
        }
        this.mailSender.send(message);
    }

    @Override
    public ResponseEntity<Map<String, Object>> createAgenTravel(Register dto) throws AgenTravelAlreadyExistException {
        Optional<AgenTravel> opt = this.repository.getAgenTravelByEmailPerusahaan(dto.emailPerusahaan());
        if(opt.isPresent()){
            throw new AgenTravelAlreadyExistException("Data agen travel sudah ada");
        }
        AgenTravel agenTravel = dto.toAgenTravel();
        agenTravel.setStatusVerifikasi(Verifikasi.MENUNGGU);
        this.repository.save(agenTravel);
        this.sendEmail(agenTravel.getEmailPerusahaan(), false, agenTravel);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("agentravel", agenTravel);
        return this.responseUtil.sendResponse("Sukses menyimpan data agen travel", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> loginAgenTravel(Login dto) throws AgenTravelNotExistException, WrongCredentialException {
        Optional<AgenTravel> opt = this.repository.getAgenTravelByEmailPerusahaan(dto.emailPerusahaan());
        if(opt.isEmpty()){
            throw new AgenTravelNotExistException("Data agen travel tidak ditemukan");
        }
        AgenTravel agenTravel = opt.get();
        if(this.bcryptUtil.comparePassword(dto.password(), agenTravel.getPassword())){
            String jwtToken = this.jwtUtil.generateJwtToken(agenTravel);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("agentravel", agenTravel);
            map.put("jwt_token", jwtToken);
            return this.responseUtil.sendResponse("Kredensial agen travel valid", HttpStatus.OK, true, map);
        } else {
            throw new WrongCredentialException("Kredensial tidak valid");
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateVerifikasiAgenTravel(Long id) {
        AgenTravel agenTravel = this.repository.findById(id).get();
        agenTravel.setIsVerified(true);
        agenTravel.setStatusVerifikasi(Verifikasi.TERVERIFIKASI);
        this.repository.save(agenTravel);
        return this.responseUtil.sendResponse("Sukses mengupdate status verifikasi agen travel", HttpStatus.OK, true, null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> lengkapiProfilAgenTravel(Long id, LengkapiProfil dto, MultipartFile file) throws AgenTravelNotExistException, IOException {
       AgenTravel agenTravel = this.repository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
       File suratIzinUsaha = new File(file.getOriginalFilename());
       FileOutputStream outputStream = new FileOutputStream(suratIzinUsaha);
       outputStream.write(file.getBytes());
       var result = this.cloudinary.uploader().upload(suratIzinUsaha, ObjectUtils.asMap("folder", "sinta"));
       agenTravel.setSuratIzinUsaha((String)result.get("public_id"));
       agenTravel.setSudahLengkapiProfil(true);
       agenTravel.setNomorTeleponPribadi(dto.noTeleponPribadi());
       AgenTravelMapper.INSTANCE.updateAgenTravelFromLengkapiProfil(dto, agenTravel);
       this.repository.save(agenTravel);
       outputStream.close();
       return this.responseUtil.sendResponse("Sukses melengkapi profil agen travel", HttpStatus.OK, true, null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAgenTravel(Long id) throws AgenTravelNotExistException {
        AgenTravel agenTravel = this.repository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("agentravel", agenTravel);
        return this.responseUtil.sendResponse("Sukses mendapatkan data agen travel", HttpStatus.OK, true, map);
    }
    
}
