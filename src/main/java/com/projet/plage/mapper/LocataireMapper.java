package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Locataire;

/**
 * 
 * @author Henri.
 * 
 * L'interface LocataireMapper nous permettra de génerer automatiquement une classe LocataireMapperImpl via mapstruct(dépendance présente dans le pom.xml).
 * Le but de cette classe sera de créer un object Locataire à partir d'un objet DTO.
 * 
 * On réalise un mapping avec listeLocationDto, c'est à dire que l'on spécifie que le champs listeLocationDto,
 * dans la classe locataireDto, correspondra au champ listeLocation dans l'objet Locataire.
 *
 */
@Mapper(componentModel="spring")
public interface LocataireMapper {
	
	LocataireMapper INSTANCE = Mappers.getMapper(LocataireMapper.class);
	
	
	@Mapping(source = "listeLocationDto", target="listeLocation" )
	Locataire toEntity(LocataireDto locataireDto);	

}
