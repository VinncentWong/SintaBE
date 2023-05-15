package com.example.sinta.app.pemesanan_dalam_negeri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.PemesananDalamNegeri;

@Repository
public interface PemesananDalamNegeriRepository extends JpaRepository<PemesananDalamNegeri, Long>{

    @Query(
        value = """
                SELECT p FROM PemesananDalamNegeri p 
                LEFT JOIN FETCH p.user
                LEFT JOIN FETCH p.paketWisata
                WHERE p.agenTravelId=:id
                """
    )
    List<PemesananDalamNegeri> getPemesananDalamNegeri(@Param("id") Long id);
}
