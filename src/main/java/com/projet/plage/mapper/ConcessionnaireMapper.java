package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.entity.Concessionnaire;

@Mapper(componentModel="spring")
public interface ConcessionnaireMapper {

	
	ConcessionnaireMapper INSTANCE = Mappers.getMapper(ConcessionnaireMapper.class);
	
	
	Concessionnaire toEntity(ConcessionnaireDto concessionnaireDto);
}
