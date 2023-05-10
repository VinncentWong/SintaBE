package com.example.sinta.app.agen_travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.AgenTravel;

@Repository
public interface AgenTravelRepository extends JpaRepository<AgenTravel, Long>{

    @Query(
        nativeQuery = true,
        value = """
                SELECT * FROM agen_travel WHERE email_perusahaan=:email LIMIT 1
                """
    )
    Optional<AgenTravel> getAgenTravelByEmailPerusahaan(@Param("email") String email);

    @Modifying
    @Query(
        nativeQuery = true,
        value = """
                UPDATE agen_travel
                SET status_verifikasi=:verifikasi
                WHERE id=:id
                """
    )
    void updateStatusVerifikasiAgenTravel(@Param("id")Long id, @Param("verifikasi") int verifikasi);
}
