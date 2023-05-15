package com.example.sinta.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@ToString(exclude = {"historyPembelian", "paketWisata", "bank", "portofolio"})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AgenTravel implements Extractable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailPerusahaan;

    private String emailPribadi;

    private String nomorTeleponPerusahaan;

    private String nomorTeleponPribadi;

    private String namaBadanUsaha;

    @JsonIgnore
    private String password;

    private String alamatBadanUsaha;

    private String nama;

    private String kontakWhatsappPic;

    private String suratIzinUsaha;

    private String bio;

    private String tentangSaya;

    private String kontakWhatsappBadanUsaha;

    private String akunInstagramBadanUsaha;

    private String akunFacebookBadanUsaha;

    private String akunTelegramBadanUsaha;

    private String akunLineBadanUsaha;

    private Boolean isVerified;

    private Boolean isPremium;

    private Boolean sudahLengkapiProfil;

    private Boolean sudahIsiDetailBank;

    private Date tanggalBerlangganan;

    private Date tanggalExpirePremium;

    private Verifikasi statusVerifikasi;

    @Enumerated(EnumType.STRING)
    private final Role role = Role.AGEN_TRAVEL;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agenTravel")
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<HistoryPembelianPremium> historyPembelian;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agenTravel")
    @JsonIgnore
    private List<PaketWisata> paketWisata;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agenTravel")
    @JsonIgnore
    private List<Bank> bank;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "agenTravel")
    @JsonIgnore
    private List<Portofolio> portofolio;
}
