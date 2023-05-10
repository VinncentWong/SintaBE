package com.example.sinta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.AgenTravel;
import com.example.sinta.dto.AgenTravelDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AgenTravelMapper {

    AgenTravelMapper INSTANCE = Mappers.getMapper(AgenTravelMapper.class);
    
    void updateAgenTravelFromLengkapiProfil(AgenTravelDto.LengkapiProfil dto, @MappingTarget AgenTravel agenTravel);
}
