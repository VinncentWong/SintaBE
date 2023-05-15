package com.example.sinta.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

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
public class PemesananDalamNegeri {
    
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

    @Enumerated(EnumType.STRING)
    private TipePembayaran tipePembayaran;

    @Enumerated(EnumType.STRING)
    private StatusPembayaran statusPembayaran;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananDalamNegeri")
    @JsonIgnore
    private List<DetailTamuDewasa> detailTamuDewasa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananDalamNegeri")
    @JsonIgnore
    private List<DetailTamuAnak> detailTamuAnak;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pemesananDalamNegeri")
    @JsonIgnore
    private List<DetailTamuBayi> detailTamuBayi;

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
