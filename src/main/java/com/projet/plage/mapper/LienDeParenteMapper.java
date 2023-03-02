package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.LienDeParenteDto;
import com.projet.plage.entity.LienDeParente;

@Mapper(componentModel = "spring")
public interface LienDeParenteMapper {
	
	LienDeParenteMapper INSTANCE = Mappers.getMapper(LienDeParenteMapper.class);
	
	LienDeParente toEntity(LienDeParenteDto lienDeParenteDto);
	


}
