package com.projet.plage.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParasolDto {
	
	private Long id;
	
	@NotNull(message="Merci de préciser le numéro d'emplacement")
	@Min(value=1, message="Le numéro doit être supérieur ou égal à 1")
	@Max(value=9, message="Le numéro doit être inférieur ou égal à 9")
	private byte numEmplacement;		
	
	@NotNull(message="Le champ ne peut pas être null")
	private FileDto fileDto;		
	
	@NotNull(message="Le champ ne peut pas être null")
	@NotEmpty(message="La liste des locations ne peut pas être vide")
	private List<LocationDto> locationsDto;

	public ParasolDto(Long id,
			byte numEmplacement,
			FileDto fileDto) {
		super();
		this.id = id;
		this.numEmplacement = numEmplacement;
		this.fileDto = fileDto;		
	}	
	
	//constructeur pour la partie location
	
	

}
