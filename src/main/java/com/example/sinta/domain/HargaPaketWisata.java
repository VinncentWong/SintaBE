package com.example.sinta.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.ToString;

@Entity
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "paketWisata")
public class HargaPaketWisata {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipeOrang tipeOrang;
    
    private Long harga;

    private Integer kuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private PaketWisata paketWisata;

    @Override
    public boolean equals(Object arg0) {
        if(arg0 instanceof HargaPaketWisata h){
            if(h.harga.equals(this.harga) && h.kuota.equals(this.harga) && h.tipeOrang.name().equals(this.tipeOrang.name())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
