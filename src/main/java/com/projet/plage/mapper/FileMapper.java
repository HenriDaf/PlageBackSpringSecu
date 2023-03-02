package com.projet.plage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.projet.plage.dto.FileDto;
import com.projet.plage.entity.File;

@Mapper(componentModel = "spring")
public interface FileMapper {
	
	FileMapper INSTANCE = Mappers.getMapper(FileMapper.class );	
	
	File toEntity(FileDto fileDto);	
    
}
