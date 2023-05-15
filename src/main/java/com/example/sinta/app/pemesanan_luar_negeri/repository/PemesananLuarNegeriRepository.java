package com.example.sinta.app.pemesanan_luar_negeri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.PemesananLuarNegeri;

@Repository
public interface PemesananLuarNegeriRepository extends JpaRepository<PemesananLuarNegeri, Long>{

    @Query(
        value = """
                SELECT p FROM PemesananLuarNegeri p 
                LEFT JOIN FETCH p.user
                LEFT JOIN FETCH p.paketWisata
                WHERE p.agenTravelId=:id
                """
    )
    List<PemesananLuarNegeri> getPemesananLuarNegeri(@Param("id") Long id);
}
