package com.example.sinta.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaketWisata {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private Integer durasiPaketWisataHari;
    
    private Integer durasiPaketWisataMalam;

    private String linkGroup;

    private String lokasiPenjemputan;

    private String gambarCover;

    @Enumerated(EnumType.STRING)
    private JenisKelengkapan jenisKelengkapan;

    @Column(length = 10_000_000)
    private String deskripsi;

    @Column(length = 10_000_000)
    private String infoPenting;

    @Column(length = 10_000_000)
    private String rundown;

    @Column(length = 10_000_000)
    private String fasilitas;

    private String infoHarga;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;

    @Enumerated(EnumType.STRING)
    private TipePaketWisata tipePaketWisata;

    @Enumerated(EnumType.STRING)
    private DomainPaketWisata domain;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paketWisata")
    private List<DetailTanggal> detailTanggal;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paketWisata")
    private List<HargaPaketWisata> hargaPaketWisata;

    @ManyToOne(fetch = FetchType.LAZY)
    private AgenTravel agenTravel;
}
