package com.example.sinta.app.paket_wisata.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.paket_wisata.repository.PaketWisataRepository;
import com.example.sinta.app.paket_wisata.service.IPaketWisataService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.DetailTanggal;
import com.example.sinta.domain.DomainPaketWisata;
import com.example.sinta.domain.HargaPaketWisata;
import com.example.sinta.domain.JenisKelengkapan;
import com.example.sinta.domain.PaketWisata;
import com.example.sinta.dto.PaketWisataDto.CreateOrUpdate;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.mapper.PaketWisataMapper;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaketWisataService implements IPaketWisataService{

    private final PaketWisataRepository repository;

    private final AgenTravelRepository agenTravelRepository;

    private final Cloudinary cloudinary;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    private final PaketWisataMapper mapper = PaketWisataMapper.INSTANCE;

    @Autowired
    public PaketWisataService(PaketWisataRepository repository, AgenTravelRepository agenTravelRepository, Cloudinary cloudinary) {
        this.repository = repository;
        this.agenTravelRepository = agenTravelRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPaketWisata(Long id, CreateOrUpdate dto, MultipartFile file) throws AgenTravelNotExistException, IOException {
        AgenTravel agenTravel = this.agenTravelRepository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
        PaketWisata paketWisata = this.mapper.getPaketWisata(dto);
        paketWisata.setAgenTravel(agenTravel);
        dto.hargaPaketWisata().forEach((d) -> {
            d.setPaketWisata(paketWisata);
        });
        dto.detailTanggal().forEach((d) -> {
            d.setPaketWisata(paketWisata);
        });
        File coverPaketWisata = new File(file.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(coverPaketWisata);
        outputStream.write(file.getBytes());
        var result = this.cloudinary.uploader().upload(coverPaketWisata, ObjectUtils.asMap("folder", "sinta"));
        outputStream.close();
        paketWisata.setGambarCover((String)result.get("secure_url"));
        paketWisata.setHargaPaketWisata(dto.hargaPaketWisata());
        this.repository.save(paketWisata);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", paketWisata);
        return this.responseUtil.sendResponse("Sukses membuat paket wisata", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisataByAgenTravelId(Long id) {
        List<PaketWisata> temp = this.repository.getPaketWisataByAgenTravelId(id);
        List<PaketWisata> data = this.repository.getPaketWisataByAgenTravelId(temp);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data paket wisata", HttpStatus.OK, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisataByPaketWisataId(Long id) {
        PaketWisata data = this.repository.findById(id).get();
        Hibernate.initialize(data.getAgenTravel());
        data.getDetailTanggal().size();
        data.getHargaPaketWisata().size();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data paket wisata", HttpStatus.OK, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisatas() {
        List<PaketWisata> temp = this.repository.getPaketWisatas();
        List<PaketWisata> data = this.repository.getPaketWisataByAgenTravelId(temp);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data paket wisata", HttpStatus.OK, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deletePaketWisata(Long id) {
        this.repository.deleteById(id);
        return this.responseUtil.sendResponse("Sukses menghapus data paket wisata", HttpStatus.OK, true, null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updatePaketWisata(Long id, CreateOrUpdate dto) {
        PaketWisata extractFromDto = this.mapper.getPaketWisata(dto);
        PaketWisata paketWisata = this.repository.findById(id).get();
        paketWisata.getDetailTanggal().size();
        paketWisata.getHargaPaketWisata().size();
        List<DetailTanggal> listDetailTanggal = paketWisata.getDetailTanggal();
        List<DetailTanggal> detailTanggalDto = dto.detailTanggal();
        for(int i = 0 ; i < listDetailTanggal.size() ; i++){
            if(i == dto.detailTanggal().size()){
                break;
            }
            DetailTanggal currentDetailTanggal = listDetailTanggal.get(i);
            DetailTanggal currentDetailTanggalDto = detailTanggalDto.get(i);
            if(!currentDetailTanggal.equals(currentDetailTanggalDto)){
                currentDetailTanggal.setTanggalMulai(currentDetailTanggalDto.getTanggalMulai());
                currentDetailTanggal.setTanggalPulang(currentDetailTanggalDto.getTanggalPulang());
            }
        }
        List<HargaPaketWisata> listHargaPaketWisata = paketWisata.getHargaPaketWisata();
        List<HargaPaketWisata> hargaPaketDto = dto.hargaPaketWisata();
        for(int i = 0 ; i < listHargaPaketWisata.size() ; i++){
            if(i == dto.hargaPaketWisata().size()){
                break;
            }
            HargaPaketWisata currentHarga = listHargaPaketWisata.get(i);
            HargaPaketWisata currentHargaDto = hargaPaketDto.get(i);
            if(!currentHarga.equals(currentHargaDto)){
                currentHarga.setHarga(currentHargaDto.getHarga());
                currentHarga.setKuota(currentHargaDto.getKuota());
                currentHarga.setTipeOrang(currentHargaDto.getTipeOrang());
            }
        }
        this.mapper.updatePaketWisata(extractFromDto, paketWisata);
        this.repository.save(paketWisata);
        return this.responseUtil.sendResponse("Sukses mengupdate data paket wisata", HttpStatus.OK, true, null);
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getPaketWisataByDomain(DomainPaketWisata domain) {
        var data = this.repository.getPaketWisataByDomain(domain);
        data.forEach((v) -> {
            Hibernate.initialize(v.getHargaPaketWisata());
        });
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data paket wisata", HttpStatus.OK, true, map);
    }

    public ResponseEntity<Map<String, Object>> getPaketWisataByJenisKelengkapan(JenisKelengkapan jenisKelengkapan){
        var data = this.repository.getPaketWisataByJenisKelengkapan(jenisKelengkapan);
        data.forEach((v) -> {
            Hibernate.initialize(v.getHargaPaketWisata());
        });
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("paket_wisata", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data paket wisata", HttpStatus.OK, true, map);
    }
}
