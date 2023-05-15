package com.example.sinta.app.portofolio.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sinta.app.agen_travel.repository.AgenTravelRepository;
import com.example.sinta.app.portofolio.repository.PortofolioRepository;
import com.example.sinta.app.portofolio.service.IPortofolioService;
import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.Portofolio;
import com.example.sinta.dto.PortofolioDto;
import com.example.sinta.util.ResponseUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PortofolioService implements IPortofolioService{
    
    private final PortofolioRepository repository;

    private final AgenTravelRepository agenTravelRepository;

    private final ResponseUtil responseUtil = ResponseUtil.INSTANCE;

    public PortofolioService(PortofolioRepository repository, AgenTravelRepository agenTravelRepository) {
        this.repository = repository;
        this.agenTravelRepository = agenTravelRepository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> create(Long id, PortofolioDto dto) {
        AgenTravel agenTravel = this.agenTravelRepository.findById(id).get();
        Portofolio portofolio = dto.toPortofolio();
        portofolio.setAgenTravel(agenTravel);
        this.repository.save(portofolio);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("portofolio", portofolio);
        return this.responseUtil.sendResponse("Sukses membuat portofolio agen travel", HttpStatus.CREATED, true, map);
    }

    @Override
    public ResponseEntity<Map<String, Object>> get(Long id) {
        List<Portofolio> portofolio = this.repository.getPortofolio(id);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("portofolio", portofolio);
        return this.responseUtil.sendResponse("Sukses membuat portofolio agen travel", HttpStatus.CREATED, true, map);
    }
}
