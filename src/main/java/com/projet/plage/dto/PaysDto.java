package com.projet.plage.dto;

import java.util.List;


import javax.validation.constraints.NotBlank;

import com.projet.plage.entity.Locataire;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@RequiredArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaysDto {
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner un code")
	String code;
		
	@NonNull
	@NotBlank(message = "Veuillez renseigner un nom de pays")
	String nom;
	

	List<Locataire> locataires;

}
