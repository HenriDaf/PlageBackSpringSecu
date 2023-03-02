package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.StatutDto;
import com.projet.plage.entity.Statut;

@Mapper(componentModel = "spring")
public interface StatutMapper {
	
	StatutMapper INSTANCE=Mappers.getMapper(StatutMapper.class);
	
	StatutDto toDto(Statut statut); 

    Statut toEntity(StatutDto statutDto);

}
