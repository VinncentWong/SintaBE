package com.example.sinta.app.pemesanan_luar_negeri.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.paket_wisata.repository.PaketWisataRepository;
import com.example.sinta.app.pemesanan_luar_negeri.repository.PemesananLuarNegeriRepository;
import com.example.sinta.app.pemesanan_luar_negeri.service.IPemesananLuarNegeriService;
import com.example.sinta.app.user.repository.UserRepository;
import com.example.sinta.domain.DetailTamuAnakLuarNegeri;
import com.example.sinta.domain.DetailTamuBayiLuarNegeri;
import com.example.sinta.domain.DetailTamuDewasaLuarNegeri;
import com.example.sinta.domain.PaketWisata;
import com.example.sinta.domain.PemesananLuarNegeri;
import com.example.sinta.domain.User;
import com.example.sinta.dto.PemesananLuarNegeriDto.Create;
import com.example.sinta.mapper.PemesananLuarNegeriMapper;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PemesananLuarNegeriService implements IPemesananLuarNegeriService{

    private final PemesananLuarNegeriRepository repository;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    private final PaketWisataRepository paketWisataRepository;

    private final UserRepository userRepository;

    private final PemesananLuarNegeriMapper mapper = PemesananLuarNegeriMapper.INSTANCE;

    public PemesananLuarNegeriService(PemesananLuarNegeriRepository repository, PaketWisataRepository paketWisataRepository, UserRepository userRepository) {
        this.repository = repository;
        this.paketWisataRepository = paketWisataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPemesanan(Long userId, Long agenTravelId, Long paketWisataId,
            Create dto) {
                PemesananLuarNegeri pemesananLuarNegeri = this.mapper.toPemesananLuarNegeri(dto);
                PaketWisata paketWisata = this.paketWisataRepository.findById(paketWisataId).get();
                User user = this.userRepository.findById(userId).get();
                pemesananLuarNegeri.setPaketWisata(paketWisata);
                pemesananLuarNegeri.setAgenTravelId(agenTravelId);
                pemesananLuarNegeri.setUser(user);
                /*
                 * Code below can be changed using JPQL Fetch Join
                 */
                Hibernate.initialize(paketWisata.getDetailTanggal());
                Hibernate.initialize(paketWisata.getHargaPaketWisata());
                Hibernate.initialize(paketWisata.getAgenTravel());
                List<DetailTamuAnakLuarNegeri> detailTamuAnak = pemesananLuarNegeri.getDetailTamuAnak();
                List<DetailTamuBayiLuarNegeri> detailTamuBayi = pemesananLuarNegeri.getDetailTamuBayi();
                List<DetailTamuDewasaLuarNegeri> detailTamuDewasa = pemesananLuarNegeri.getDetailTamuDewasa();
                detailTamuAnak.forEach((d) -> {
                    d.setPemesananLuarNegeri(pemesananLuarNegeri);
                });
                detailTamuBayi.forEach((d) -> {
                    d.setPemesananLuarNegeri(pemesananLuarNegeri);
                });
                detailTamuDewasa.forEach((d) -> {
                    d.setPemesananLuarNegeri(pemesananLuarNegeri);
                });
                this.repository.save(pemesananLuarNegeri);
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pemesanan_luar_negeri", pemesananLuarNegeri);
                return this.responseUtil.sendResponse("Sukses membuat pemesanan dalam negeri", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getPemesanan(Long id) {
        List<PemesananLuarNegeri> pemesananLuarNegeri = this.repository.getPemesananLuarNegeri(id);
        pemesananLuarNegeri.forEach((d) -> {
            d.initialize();
        });
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pemesanan_dalam_negeri", pemesananLuarNegeri);
        return this.responseUtil.sendResponse("Sukses mendapatkan pemesanan dalam negeri", HttpStatus.OK, true, map);
    }
    
}
