package com.example.sinta.app.bank.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.bank.repository.BankRepository;
import com.example.sinta.app.bank.service.IBankService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.Bank;
import com.example.sinta.dto.BankDto.CreateOrUpdate;
import com.example.sinta.exception.AgenTravelNotExistException;
import com.example.sinta.mapper.BankMapper;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankService implements IBankService{

    private final BankRepository repository;

    private final AgenTravelRepository agenTravelRepository;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    public BankService(BankRepository repository, AgenTravelRepository agenTravelRepository) {
        this.repository = repository;
        this.agenTravelRepository = agenTravelRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createBank(Long id, CreateOrUpdate dto) throws AgenTravelNotExistException {
        AgenTravel agenTravel = this.agenTravelRepository.findById(id).orElseThrow(() -> new AgenTravelNotExistException("Data agen travel tidak ditemukan"));
        Bank bank = dto.toBank();
        bank.setAgenTravel(agenTravel);
        agenTravel.setSudahIsiDetailBank(true);
        agenTravel.getBank().add(bank);
        var result = this.agenTravelRepository.save(agenTravel);
        Map<String, Object> map = new LinkedHashMap<>();
        var resultBank = result.getBank();
        var responseBank = switch(resultBank.size()){
            case 0 -> resultBank.get(0);
            default -> resultBank.get(resultBank.size() - 1);
        };
        map.put("bank", responseBank);
        return this.responseUtil.sendResponse("Sukses membuat data bank", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBank(Long id) {
        var result = this.repository.getBank(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("bank", result);
        return this.responseUtil.sendResponse("Sukses mendapatkan data bank", HttpStatus.OK, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBank(Long idBank, CreateOrUpdate dto) {
        Bank bank = this.repository.findById(idBank).get();
        Bank extractFromDto = BankMapper.INSTANCE.getBank(dto);
        BankMapper.INSTANCE.updateBank(extractFromDto, bank);
        this.repository.save(bank);
        return this.responseUtil.sendResponse("Sukses mengupdate data bank", HttpStatus.OK, true, null);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBank(Long id) {
        this.repository.deleteById(id);
        return this.responseUtil.sendResponse("Sukses menghapus data bank", HttpStatus.OK, true, null);
    }
    
}
