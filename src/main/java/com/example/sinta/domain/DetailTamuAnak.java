package com.example.sinta.domain;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
public class DetailTamuAnak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Titel titel;

    private String nama;

    private Date tanggalLahir;

    @ManyToOne(fetch = FetchType.LAZY)
    private PemesananDalamNegeri pemesananDalamNegeri;

    @ManyToOne(fetch = FetchType.LAZY)
    private PemesananLuarNegeri pemesananLuarNegeri;
}
