package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.ParasolDto;
import com.projet.plage.entity.Parasol;

@Mapper(componentModel = "spring", uses= {FileMapper.class})
public interface ParasolMapper {
	
	ParasolMapper INSTANCE = Mappers.getMapper(ParasolMapper.class );
	

	@Mapping(source = "fileDto", target = "file")
	Parasol toEntity(ParasolDto parasolDto);    

}
