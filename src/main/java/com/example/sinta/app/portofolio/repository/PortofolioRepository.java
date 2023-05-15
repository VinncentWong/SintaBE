package com.example.sinta.app.portofolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.Portofolio;

@Repository
public interface PortofolioRepository extends JpaRepository<Portofolio, Long>{

    @Query(
        value = """
                SELECT p from Portofolio p LEFT JOIN FETCH p.agenTravel WHERE p.agenTravel.id=:id
                """
    )
    List<Portofolio> getPortofolio(@Param("id") Long id);
}
