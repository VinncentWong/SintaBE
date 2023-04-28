package com.example.sinta.dto;

import com.example.sinta.domain.User;
import com.example.sinta.domain.Verifikasi;
import com.example.sinta.util.BcryptUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UserDto {

    public static final UserDto INSTANCE;

    static{
        INSTANCE = new UserDto();
    }

    private UserDto(){}
    public record Create(
            @NotNull(message = "Email harus ada")
            @NotBlank(message = "Email tidak boleh kosong")
            @Email(message = "Email anda tidak valid")
            String email,

            @NotNull(message = "Nomor telepon anda harus ada")
            @NotBlank(message = "Nomor telepon anda tidak boleh kosong")
            @Length(min = 10, max = 16, message = "Jumlah digit nomor telepon harus diantara 10 <= noTelp <= 16")
            String noTelepon,

            @NotNull(message = "Nama anda harus ada")
            @NotBlank(message = "Nama anda tidak boleh kosong")
            @Length(min = 4, message = "Panjang nama anda minimal 4")
            String nama,

            @NotNull(message = "Password harus ada")
            @NotBlank(message = "Password tidak boleh kosong")
            @Length(min = 4, message = "Panjang password minimal 4")
            String password
    ){
        public User toUser(){
            User user = new User();
            user.setEmail(this.email);
            user.setPassword(BcryptUtil.INSTANCE.generateBcryptPassword(this.password));
            user.setVerified(Verifikasi.MENUNGGU);
            user.setNoTelp(this.noTelepon);
            user.setNama(this.nama);
            return user;
        }
    }

    public record Login(
            @NotNull(message = "Email anda harus ada")
            @NotBlank(message = "Email anda tidak boleh kosong")
            String email,

            @NotNull(message = "Password harus ada")
            @NotBlank(message = "Password tidak boleh kosong")
            String password
    ){}
}
