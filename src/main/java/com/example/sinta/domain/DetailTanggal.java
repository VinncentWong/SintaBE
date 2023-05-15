package com.example.sinta.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString(exclude = "paketWisata")
public class DetailTanggal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date tanggalMulai;

    private Date tanggalPulang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private PaketWisata paketWisata;

    @Override
    public boolean equals(Object arg0) {
        if(arg0 instanceof DetailTanggal d){
            if(this.tanggalMulai.equals(d.getTanggalMulai()) && this.tanggalPulang.equals(d.getTanggalPulang())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
   
}
