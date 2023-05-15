package com.example.sinta.app.paket_wisata.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.paket_wisata.repository.PaketWisataRepository;
import com.example.sinta.app.paket_wisata.service.IPaketWisataService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.DetailTanggal;
import com.example.sinta.domain.HargaPaketWisata;
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

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    private final PaketWisataMapper mapper = PaketWisataMapper.INSTANCE;

    public PaketWisataService(PaketWisataRepository repository, AgenTravelRepository agenTravelRepository) {
        this.repository = repository;
        this.agenTravelRepository = agenTravelRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createPaketWisata(Long id, CreateOrUpdate dto) throws AgenTravelNotExistException {
        AgenTravel agenTravel = this.agenTravelRepository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
        PaketWisata paketWisata = this.mapper.getPaketWisata(dto);
        paketWisata.setAgenTravel(agenTravel);
        dto.hargaPaketWisata().forEach((d) -> {
            d.setPaketWisata(paketWisata);
        });
        dto.detailTanggal().forEach((d) -> {
            d.setPaketWisata(paketWisata);
        });
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
    
}
