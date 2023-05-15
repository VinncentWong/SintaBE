package com.example.sinta.dto;

import org.hibernate.validator.constraints.Range;

import com.example.sinta.domain.HistoryPembelianPremium;
import com.example.sinta.domain.TipePembayaran;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HistoryPembelianPremiumDto {
    
    public record Create(
        @NotBlank
        @NotNull
        String tipePembayaran,

        @Range(min = 1, max = 6, message = "Minimal lama premium adalah 1 dan maksimal 6")
        Long lamaPremium,

        @Range(min = 0, max = 400000, message = "Minimal total pembayaran adalah 0 dan maksimal 400000")
        Long totalPembayaran
    ){
        public HistoryPembelianPremium toHistoryPembelianPremium(){
            TipePembayaran tipePembayaran = switch(this.tipePembayaran){
                case "Gopay" -> TipePembayaran.Gopay;
                case "Dana" -> TipePembayaran.Dana;
                case "Shopeepay" -> TipePembayaran.Shopeepay;
                default -> TipePembayaran.Ovo;
            };
            return HistoryPembelianPremium.builder()
                                        .lamaPremium(this.lamaPremium)
                                        .tipePembayaran(tipePembayaran)
                                        .totalPembayaran(totalPembayaran)
                                        .build();
        }
    }
}
