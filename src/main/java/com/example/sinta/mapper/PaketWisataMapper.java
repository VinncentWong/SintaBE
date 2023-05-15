package com.example.sinta.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.PaketWisata;
import com.example.sinta.dto.PaketWisataDto;

@Mapper
public interface PaketWisataMapper {

    PaketWisataMapper INSTANCE = Mappers.getMapper(PaketWisataMapper.class);
    
    PaketWisata getPaketWisata(PaketWisataDto.CreateOrUpdate dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "detailTanggal", ignore = true)
    @Mapping(target = "hargaPaketWisata", ignore = true)
    void updatePaketWisata(PaketWisata extract, @MappingTarget PaketWisata paketWisata);
}
