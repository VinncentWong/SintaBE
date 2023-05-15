package com.example.sinta.app.history_pembelian_premium.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.history_pembelian_premium.repository.HistoryPembelianPremiumRepository;
import com.example.sinta.app.history_pembelian_premium.service.IHistoryPembelianPremiumService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.HistoryPembelianPremium;
import com.example.sinta.dto.HistoryPembelianPremiumDto;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.exception.HistoryPembelianPremiumNotFoundException;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoryPembelianPremiumService implements IHistoryPembelianPremiumService{
    
    private final HistoryPembelianPremiumRepository repository;

    private final AgenTravelRepository agenTravelRepository;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    @Autowired
    public HistoryPembelianPremiumService(HistoryPembelianPremiumRepository repository, AgenTravelRepository agenTravelRepository) {
        this.repository = repository;
        this.agenTravelRepository = agenTravelRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createHistoryPembelian(Long id, HistoryPembelianPremiumDto.Create dto) throws AgenTravelNotExistException {
        AgenTravel agenTravel = this.agenTravelRepository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
        HistoryPembelianPremium pembelian = dto.toHistoryPembelianPremium();
        agenTravel.setIsPremium(true);
        agenTravel.setTanggalBerlangganan(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, dto.lamaPremium().intValue());
        agenTravel.setTanggalExpirePremium(calendar.getTime());
        List<HistoryPembelianPremium> list = agenTravel.getHistoryPembelian();
        list.size();
        list.add(pembelian);
        agenTravel.setHistoryPembelian(list);
        pembelian.setAgenTravel(agenTravel);
        var result = this.agenTravelRepository.save(agenTravel);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pembelian_premium", result.getHistoryPembelian().get(result.getHistoryPembelian().size() - 1));
        return this.responseUtil.sendResponse("Sukses membuat data pembelian premium", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getHistoryPembelian(Long id) throws AgenTravelNotExistException, HistoryPembelianPremiumNotFoundException {
        List<HistoryPembelianPremium> data = this.repository.findHistoryPembelian(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("history_pembelian_premium", data);
        return this.responseUtil.sendResponse("Sukses mendapatkan data pembelian premium", HttpStatus.OK, true, map);
    }
}
