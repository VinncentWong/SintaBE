package com.example.sinta.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@ToString(exclude = {"detailTamuDewasa", "detailTamuAnak", "detailTamuBayi"})
public class PemesananLuarNegeri {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Titel titel;

    private String namaDepanTengah;

    private String namaBelakang;

    private Date tanggalLahir;

    /***
     * This property for a while will always have value "Indonesia"
     */
    private final String kewarganegaraan = "Indonesia";

    private String nomorPaspor;

    private Date tanggalHabisBerlakuPaspor;

    @Enumerated(EnumType.STRING)
    private TipePembayaran tipePembayaran;

    @Enumerated(EnumType.STRING)
    private StatusPembayaran statusPembayaran;

    @CreationTimestamp
    private Date createdAt;

    private Date deletedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuDewasa> detailTamuDewasa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuAnak> detailTamuAnak;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuBayi> detailTamuBayi;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private PaketWisata paketWisata;
}
