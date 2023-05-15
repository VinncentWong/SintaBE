package com.example.sinta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.PemesananLuarNegeri;
import com.example.sinta.dto.PemesananLuarNegeriDto;

@Mapper
public interface PemesananLuarNegeriMapper {
    
    PemesananLuarNegeriMapper INSTANCE = Mappers.getMapper(PemesananLuarNegeriMapper.class);

    public PemesananLuarNegeri toPemesananLuarNegeri(PemesananLuarNegeriDto.Create dto);
}
