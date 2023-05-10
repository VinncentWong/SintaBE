package com.example.sinta.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
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
@ToString(exclude = {"hargaPaketWisata"})
public class PaketWisata {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private Integer durasiPaketWisataHari;
    
    private Integer durasiPaketWisataMalam;

    private String linkGroup;

    private String lokasiPenjemputan;

    @Enumerated(EnumType.STRING)
    private JenisKelengkapan jenisKelengkapan;

    private String deskripsi;

    private String infoPenting;

    private String rundown;

    private String fasilitas;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;

    @ElementCollection
    @MapKeyColumn
    @CollectionTable(name = "detail_tanggal", joinColumns = @JoinColumn(name = "id_paket_wisata"))
    private Map<Date, Date> detailTanggal;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paketWisata")
    @JsonIgnore
    private List<HargaPaketWisata> hargaPaketWisata;

    @ManyToOne(fetch = FetchType.LAZY)
    private AgenTravel agenTravel;
}
