package com.example.sinta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.example.sinta.domain.User;
import com.example.sinta.dto.UserDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "nomorKtp", target = "nomorKtp")
    @Mapping(source = "nama", target = "nama")
    @Mapping(source = "noTelp", target = "noTelp")
    void updateUser(UserDto.Update dto, @MappingTarget User user);
}
