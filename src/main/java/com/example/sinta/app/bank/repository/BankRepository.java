package com.example.sinta.app.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sinta.domain.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

    @Query(
        value = """
                SELECT b from Bank b join fetch b.agenTravel WHERE b.agenTravel.id=:id
                """
    )
    List<Bank> getBank(@Param("id") Long id);
}
