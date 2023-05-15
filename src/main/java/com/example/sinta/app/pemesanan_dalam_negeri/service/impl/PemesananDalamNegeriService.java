package com.example.sinta.app.pemesanan_dalam_negeri.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.paket_wisata.repository.PaketWisataRepository;
import com.example.sinta.app.pemesanan_dalam_negeri.repository.PemesananDalamNegeriRepository;
import com.example.sinta.app.pemesanan_dalam_negeri.service.IPemesananDalamNegeriService;
import com.example.sinta.app.user.repository.UserRepository;
import com.example.sinta.domain.DetailTamuAnak;
import com.example.sinta.domain.DetailTamuBayi;
import com.example.sinta.domain.DetailTamuDewasa;
import com.example.sinta.domain.PaketWisata;
import com.example.sinta.domain.PemesananDalamNegeri;
import com.example.sinta.domain.User;
import com.example.sinta.dto.PemesananDalamNegeriDto.Create;
import com.example.sinta.mapper.PemesananDalamNegeriMapper;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PemesananDalamNegeriService implements IPemesananDalamNegeriService{

    private final PemesananDalamNegeriRepository repository;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    private final PaketWisataRepository paketWisataRepository;

    private final UserRepository userRepository;

    private final PemesananDalamNegeriMapper mapper = PemesananDalamNegeriMapper.INSTANCE;

    public PemesananDalamNegeriService(PemesananDalamNegeriRepository repository, PaketWisataRepository paketWisataRepository, UserRepository userRepository) {
        this.repository = repository;
        this.paketWisataRepository = paketWisataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPemesanan(Long userId, Long agenTravelId, Long paketWisataId, Create dto) {
        PemesananDalamNegeri pemesananDalamNegeri = this.mapper.toPemesananDalamNegeri(dto);
        PaketWisata paketWisata = this.paketWisataRepository.findById(paketWisataId).get();
        User user = this.userRepository.findById(userId).get();
        pemesananDalamNegeri.setPaketWisata(paketWisata);
        pemesananDalamNegeri.setAgenTravelId(agenTravelId);
        pemesananDalamNegeri.setUser(user);
        /*
         * Code below can be changed using JPQL Fetch Join
         */
        Hibernate.initialize(paketWisata.getDetailTanggal());
        Hibernate.initialize(paketWisata.getHargaPaketWisata());
        Hibernate.initialize(paketWisata.getAgenTravel());
        List<DetailTamuAnak> detailTamuAnak = pemesananDalamNegeri.getDetailTamuAnak();
        List<DetailTamuBayi> detailTamuBayi = pemesananDalamNegeri.getDetailTamuBayi();
        List<DetailTamuDewasa> detailTamuDewasa = pemesananDalamNegeri.getDetailTamuDewasa();
        detailTamuAnak.forEach((d) -> {
            d.setPemesananDalamNegeri(pemesananDalamNegeri);
        });
        detailTamuBayi.forEach((d) -> {
            d.setPemesananDalamNegeri(pemesananDalamNegeri);
        });
        detailTamuDewasa.forEach((d) -> {
            d.setPemesananDalamNegeri(pemesananDalamNegeri);
        });
        this.repository.save(pemesananDalamNegeri);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pemesanan_dalam_negeri", pemesananDalamNegeri);
        return this.responseUtil.sendResponse("Sukses membuat pemesanan dalam negeri", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getPemesanan(Long id) {
        List<PemesananDalamNegeri> pemesananDalamNegeri = this.repository.getPemesananDalamNegeri(id);
        pemesananDalamNegeri.forEach((d) -> {
            d.initialize();
        });
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pemesanan_dalam_negeri", pemesananDalamNegeri);
        return this.responseUtil.sendResponse("Sukses mendapatkan pemesanan dalam negeri", HttpStatus.OK, true, map);
    }
    
}
