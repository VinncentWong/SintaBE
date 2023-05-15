package com.example.sinta.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
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

    private String namaLengkap;

    private String email;

    private String nomorKtp;

    private String nomorTelepon;

    private Integer banyakDewasa;

    private Integer banyakAnak;

    private Integer banyakBayi;

    /***
     * This property for a while will always have value "Indonesia"
     */
    private final String kewarganegaraan = "Indonesia";

    @Enumerated(EnumType.STRING)
    private TipePembayaran tipePembayaran;

    @Enumerated(EnumType.STRING)
    private StatusPembayaran statusPembayaran;

    @CreationTimestamp
    private Date createdAt;

    private Date deletedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuDewasaLuarNegeri> detailTamuDewasa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuAnakLuarNegeri> detailTamuAnak;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananLuarNegeri")
    @JsonIgnore
    private List<DetailTamuBayiLuarNegeri> detailTamuBayi;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private PaketWisata paketWisata;

    private Long agenTravelId;

    public void initialize(){
        Hibernate.initialize(this.paketWisata.getAgenTravel());
        Hibernate.initialize(this.paketWisata.getDetailTanggal());
        Hibernate.initialize(this.paketWisata.getHargaPaketWisata());
    }   
}
