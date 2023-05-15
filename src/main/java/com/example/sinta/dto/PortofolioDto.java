package com.example.sinta.dto;

import com.example.sinta.domain.Portofolio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PortofolioDto(
    @NotNull(message = "teks harus ada")
    @NotBlank(message = "teks tidak boleh kosong")
    String text
){
    public Portofolio toPortofolio(){
        Portofolio portofolio = new Portofolio();
        portofolio.setText(this.text);
        return portofolio;
    }
}