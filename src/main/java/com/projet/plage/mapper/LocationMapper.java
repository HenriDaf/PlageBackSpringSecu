package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.LocationDto;
import com.projet.plage.entity.Location;

@Mapper(componentModel="spring", uses = {StatutMapper.class, ParasolMapper.class, LocataireMapper.class})
public interface LocationMapper {
	
	LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
	
	@Mapping(source = "locataireDto", target="locataire" )
	@Mapping(source = "listeParasolDto", target="listeParasol" )	
	@Mapping(source = "statutDto", target="statut" )
	Location toEntity(LocationDto locationDto);

}
