package com.example.sinta.dto;

import com.example.sinta.domain.Bank;
import com.example.sinta.domain.TipePembayaran;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BankDto {
    
    public record CreateOrUpdate(
        @NotNull(message = "Nama harus ada")
        @NotBlank(message = "Nama tidak boleh kosong")
        String nama,

        @NotNull(message = "Nomor rekening harus ada")
        @NotBlank(message = "Nomor rekening tidak boleh kosong")
        String nomorRekening,

        @NotNull(message = "Provider harus ada")
        @NotBlank(message = "Provider tidak boleh kosong")
        String provider
    ){
        public Bank toBank(){
            TipePembayaran pembayaran = switch(provider){
                case "Gopay" -> TipePembayaran.Gopay;
                case "Dana" -> TipePembayaran.Dana;
                case "Ovo" -> TipePembayaran.Ovo;
                default -> TipePembayaran.Shopeepay;
            };
            return Bank.builder()
                        .nama(this.nama)
                        .nomorRekening(this.nomorRekening)
                        .provider(pembayaran)
                        .build();
        }
    }
}
