package com.example.sinta.dto;

import java.util.Date;
import java.util.List;

import com.example.sinta.domain.DetailTamuAnakLuarNegeri;
import com.example.sinta.domain.DetailTamuBayiLuarNegeri;
import com.example.sinta.domain.DetailTamuDewasaLuarNegeri;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PemesananLuarNegeriDto {
    public record Create(
        @NotNull(message = "Titel harus ada")
        @NotBlank(message = "Titel tidak boleh kosong")
        String titel,

        @NotNull(message = "Nama lengkap harus ada")
        @NotBlank(message = "Nama lengkap tidak boleh kosong")
        String namaLengkap,

        @NotNull(message = "Email harus ada")
        @NotBlank(message = "Email tidak boleh kosong")
        String email,

        @NotNull(message = "Nomor ktp harus ada")
        @NotBlank(message = "Nomor ktp tidak boleh kosong")
        String nomorKtp,

        @NotNull(message = "Nomor telepon harus ada")
        @NotBlank(message = "Nomor telepon tidak boleh kosong")
        String nomorTelepon,

        @NotNull(message = "Tipe pembayaran harus ada")
        @NotBlank(message = "Tipe pembayaran tidak boleh kosong")
        String tipePembayaran,

        @NotNull(message = "Tanggal mulai harus ada")
        Date tanggalMulai,

        @NotNull(message = "Tanggal pulang harus ada")
        Date tanggalPulang,

        @NotNull(message = "Detail tamu dewasa harus ada")
        List<DetailTamuDewasaLuarNegeri> detailTamuDewasa,

        @NotNull(message = "Detail tamu anak harus ada")
        List<DetailTamuAnakLuarNegeri> detailTamuAnak,

        @NotNull(message = "Detail tamu bayi harus ada")
        List<DetailTamuBayiLuarNegeri> detailTamuBayi,

        @Min(value = 0, message = "Banyak anak minimal 0")
        Integer banyakAnak,

        @Min(value = 0, message = "Banyak dewasa minimal 0")
        Integer banyakDewasa,

        @Min(value = 0, message = "Banyak bayi minimal 0")
        Integer banyakBayi
    ){}
}
