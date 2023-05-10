package com.example.sinta.dto;

import org.hibernate.validator.constraints.Length;

import com.example.sinta.domain.AgenTravel;
import com.example.sinta.util.BcryptUtil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AgenTravelDto {

    private AgenTravelDto(){}

    public static final BcryptUtil util = BcryptUtil.INSTANCE;
    
    public record Register(
        @NotNull(message = "Email harus ada")
        @NotBlank(message = "Email tidak boleh kosong")
        @Email(message = "Email anda tidak valid")
        String emailPerusahaan,

        @NotNull(message = "Nomor telepon anda harus ada")
        @NotBlank(message = "Nomor telepon anda tidak boleh kosong")
        @Length(min = 10, max = 16, message = "Jumlah digit nomor telepon harus diantara 10 <= noTelp <= 16")
        String noTeleponPerusahaan,

        @NotNull(message = "Nama badan usaha harus ada")
        @NotBlank(message = "Nama badan usaha tidak boleh kosong")
        @Length(min = 4, message = "Panjang nama badan usaha minimal 4")
        String namaBadanUsaha,

        @NotNull(message = "Password harus ada")
        @NotBlank(message = "Password tidak boleh kosong")
        @Length(min = 4, message = "Panjang password minimal 4")
        String password
    ){
        public AgenTravel toAgenTravel(){
            AgenTravel agenTravel = new AgenTravel();
            agenTravel.setNamaBadanUsaha(this.namaBadanUsaha);
            agenTravel.setNomorTeleponPerusahaan(this.noTeleponPerusahaan);
            agenTravel.setPassword(util.generateBcryptPassword(this.password));
            agenTravel.setEmailPerusahaan(this.emailPerusahaan);
            return agenTravel;
        }
    }

    public record Login(
        @NotNull(message = "Email harus ada")
        @NotBlank(message = "Email tidak boleh kosong")
        String emailPerusahaan,

        @NotNull(message = "Password harus ada")
        @NotBlank(message = "Password tidak boleh kosong")
        String password
    ){}

    public record LengkapiProfil(
        @NotNull(message = "Nama badan usaha harus ada")
        @NotBlank(message = "Nama badan usaha tidak boleh kosong")
        @Length(min = 4, message = "Panjang nama badan usaha minimal 4")
        String namaBadanUsaha,

        @NotNull(message = "Alamat badan usaha harus ada")
        @NotBlank(message = "Alamat badan usaha tidak boleh kosong")
        @Length(min = 4, message = "Panjang alamat badan usaha minimal 4")
        String alamatBadanUsaha,

        @NotNull(message = "Nama PIC harus ada")
        @NotBlank(message = "Nama PIC tidak boleh kosong")
        @Length(min = 4, message = "Panjang nama PIC minimal 4")
        String nama,

        @NotNull(message = "Kontak whatsapp PIC harus ada")
        @NotBlank(message = "Kontak whatsapp PIC tidak boleh kosong")
        @Length(min = 10, message = "Panjang kontak whatsapp PIC minimal 10")
        String kontakWhatsappPic,

        @NotNull(message = "Bio harus ada")
        @NotBlank(message = "Bio tidak boleh kosong")
        @Length(min = 4, message = "Panjang bio minimal 4")
        String bio,

        @NotNull(message = "Tentang saya harus ada")
        @NotBlank(message = "Tentang saya tidak boleh kosong")
        @Length(min = 4, message = "Panjang tentang saya minimal 4")
        String tentangSaya,

        @NotNull(message = "Email harus ada")
        @NotBlank(message = "Email tidak boleh kosong")
        @Email
        String emailPribadi,

        @NotNull(message = "Nomor telepon pribadi harus ada")
        @NotBlank(message = "Nomor telepon pribadi tidak boleh kosong")
        @Length(min = 10, message = "Panjang nomor telepon pribadi minimal 4")
        String noTeleponPribadi,

        @NotNull(message = "Kontak whatsapp badan usaha harus ada")
        @NotBlank(message = "Kontak whatsapp badan usaha tidak boleh kosong")
        @Length(min = 4, message = "Panjang kontak whatsapp badan usaha minimal 4")
        String kontakWhatsappBadanUsaha,
        String akunInstagramBadanUsaha,
        String akunFacebookBadanUsaha,
        String akunTelegramBadanUsaha,
        String akunLineBadanUsaha
    ){}
}
