package com.example.sinta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.PemesananDalamNegeri;
import com.example.sinta.dto.PemesananDalamNegeriDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PemesananDalamNegeriMapper {
    
    public PemesananDalamNegeriMapper INSTANCE = Mappers.getMapper(PemesananDalamNegeriMapper.class);

    public PemesananDalamNegeri toPemesananDalamNegeri(PemesananDalamNegeriDto.Create dto);
}
