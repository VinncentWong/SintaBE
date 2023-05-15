package com.example.sinta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.Bank;
import com.example.sinta.dto.BankDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BankMapper {
    
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    public void updateBank(Bank transfromFromDto, @MappingTarget Bank bank);

    public Bank getBank(BankDto.CreateOrUpdate dto);
}
