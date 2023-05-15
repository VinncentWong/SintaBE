package com.example.sinta.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.example.sinta.domain.DetailTanggal;
import com.example.sinta.domain.HargaPaketWisata;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaketWisataDto {
    
    public record CreateOrUpdate(
        @NotNull(message = "Tipe paket wisata harus ada")
        @NotBlank(message = "Tipe paket wisata tidak boleh kosong")
        String tipePaketWisata,

        @NotNull(message = "Nama harus ada")
        @NotBlank(message = "Nama tidak boleh kosong")
        @Length(min = 4, message = "Minimal panjang nama adalah 4")
        String nama,

        @NotNull(message = "Nama harus ada")
        @NotBlank(message = "Nama tidak boleh kosong")
        String domain,

        @NotNull(message = "Durasi hari harus ada")
        @Min(value = 0, message = "Durasi paket wisata dalam hari tidak boleh kurang dari 0")
        Integer durasiPaketWisataHari,

        @NotNull(message = "Durasi malam harus ada")
        @Min(value = 0, message = "Durasi paket wisata dalam malam tidak boleh kurang dari 0")
        Integer durasiPaketWisataMalam,

        @NotNull(message = "Link group harus ada")
        @NotBlank(message = "Link group tidak boleh kosong")
        String linkGroup,

        @NotNull(message = "Lokasi penjemputan harus ada")
        @NotBlank(message = "Lokasi penjemputan tidak boleh kosong")
        String lokasiPenjemputan,

        @NotNull(message = "Detail tanggal harus ada")
        List<DetailTanggal> detailTanggal,

        @NotNull(message = "Harga paket wisata harus ada")
        List<HargaPaketWisata> hargaPaketWisata,

        @NotNull(message = "Jenis kelengkapan harus ada")
        @NotBlank(message = "Jenis kelengkapan tidak boleh kosong")
        String jenisKelengkapan,

        @NotNull(message = "Deskripsi harus ada")
        @NotBlank(message = "Deskripsi tidak boleh kosong")
        String deskripsi,

        @NotNull(message = "Info penting harus ada")
        @NotBlank(message = "Info penting tidak boleh kosong")
        String infoPenting,

        @NotNull(message = "Rundown harus ada")
        @NotBlank(message = "Rundown tidak boleh kosong")
        String rundown,

        @NotNull(message = "Fasilitas harus ada")
        @NotBlank(message = "Fasilitas tidak boleh kosong")
        String fasilitas,

        @NotNull(message = "Info harga harus ada")
        @NotBlank(message = "Info harga tidak boleh kosong")
        String infoHarga
    ){}
}
