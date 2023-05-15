package com.example.sinta.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class DetailTamuBayiLuarNegeri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Titel titel;

    private String namaDepanTengah;

    private String namaBelakang;

    private Date tanggalLahir;

    private String kewarganegaraan;

    private String nomorPaspor;

    private Date tanggalHabisBerlaku;

    @ManyToOne(fetch = FetchType.LAZY)
    private PemesananLuarNegeri pemesananLuarNegeri;
}
