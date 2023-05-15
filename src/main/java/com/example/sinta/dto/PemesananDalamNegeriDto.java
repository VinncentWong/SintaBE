package com.example.sinta.dto;

import java.util.Date;
import java.util.List;

import com.example.sinta.domain.DetailTamuAnak;
import com.example.sinta.domain.DetailTamuBayi;
import com.example.sinta.domain.DetailTamuDewasa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PemesananDalamNegeriDto {
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
        List<DetailTamuDewasa> detailTamuDewasa,

        @NotNull(message = "Detail tamu anak harus ada")
        List<DetailTamuAnak> detailTamuAnak,

        @NotNull(message = "Detail tamu bayi harus ada")
        List<DetailTamuBayi> detailTamuBayi,

        @Min(value = 0, message = "Banyak anak minimal 0")
        Integer banyakAnak,

        @Min(value = 0, message = "Banyak dewasa minimal 0")
        Integer banyakDewasa,

        @Min(value = 0, message = "Banyak bayi minimal 0")
        Integer banyakBayi
    ){}
}
