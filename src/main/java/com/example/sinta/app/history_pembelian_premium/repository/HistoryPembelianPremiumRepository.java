package com.example.sinta.app.history_pembelian_premium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.HistoryPembelianPremium;

@Repository
public interface HistoryPembelianPremiumRepository extends JpaRepository<HistoryPembelianPremium, Long>{

    @Query(
        value = """
                SELECT p from HistoryPembelianPremium p JOIN FETCH p.agenTravel WHERE p.agenTravel.id=:id
                """
    )
    List<HistoryPembelianPremium> findHistoryPembelian(@Param("id") Long id);
}
