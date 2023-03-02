package com.projet.plage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatutDto {
	
	@NotBlank(message = "Veuillez renseigner un statut")
	@NotNull(message="Merci de préciser le nom du statut")
	private String nom;

	public StatutDto(
			String nom) {
		super();
		this.nom = nom;
	}
	

}
