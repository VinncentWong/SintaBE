package com.example.sinta.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HargaPaketWisata {
    
    @Id
    private TipeOrang tipeOrang;
    
    private Long harga;

    private Integer kuota;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaketWisata paketWisata;
}
