package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.PaysDto;
import com.projet.plage.entity.Pays;

@Mapper(componentModel = "spring")
public interface PaysMapper {
	
	PaysMapper INSTANCE=Mappers.getMapper(PaysMapper.class);
	
	
    Pays toEntity(PaysDto paysDto);
   
}
