package com.example.sinta.app.paket_wisata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.DomainPaketWisata;
import com.example.sinta.domain.JenisKelengkapan;
import com.example.sinta.domain.PaketWisata;

@Repository
public interface PaketWisataRepository extends JpaRepository<PaketWisata, Long>{

    @Query(
        """
            SELECT p from PaketWisata p 
            LEFT JOIN FETCH p.agenTravel 
            LEFT JOIN FETCH p.detailTanggal
            WHERE p.agenTravel.id=:id
        """
    )
    List<PaketWisata> getPaketWisataByAgenTravelId(@Param("id") Long id);

    @Query(
        """
            SELECT p from PaketWisata p
            LEFT JOIN FETCH p.hargaPaketWisata
            WHERE p in :post
        """
    )
    List<PaketWisata> getPaketWisataByAgenTravelId(@Param("post") List<PaketWisata> p);

    @Query(
        """
            SELECT p from PaketWisata p 
            LEFT JOIN FETCH p.agenTravel 
            LEFT JOIN FETCH p.detailTanggal
        """
    )
    List<PaketWisata> getPaketWisatas();

    @Query(
        """
            SELECT p from PaketWisata p
            LEFT JOIN FETCH p.agenTravel
            LEFT JOIN FETCH p.detailTanggal
            WHERE p.domain=:domain        
        """
    )
    List<PaketWisata> getPaketWisataByDomain(@Param("domain") DomainPaketWisata domain);

    @Query(
        """
            SELECT p from PaketWisata p
            LEFT JOIN FETCH p.agenTravel
            LEFT JOIN FETCH p.detailTanggal
            WHERE p.jenisKelengkapan=:jenisKelengkapan        
        """
    )
    List<PaketWisata> getPaketWisataByJenisKelengkapan(@Param("jenisKelengkapan") JenisKelengkapan jenisKelengkapan);
}
