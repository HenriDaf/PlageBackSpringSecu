package com.projet.plage.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileDto {
	
	private Long id;
	
	@NotNull(message="Merci de préciser le numéro de file")
	@Min(value=1, message="Le numéro doit être supérieur ou égal à 1")
	@Max(value=8, message="Le numéro doit être inférieur ou égal à 8")
	private byte numero;
	
	@NotNull(message="Merci de préciser le prix")
	@Positive(message="Merci de préciser un nombre strictement positif")
	@Min(value=15, message="Le prix doit être supérieur ou égal à 15 euros")
	private double prixJournalier;	
	
	@NotEmpty(message="La liste de parasols ne peut être vide")
	private List<ParasolDto> listeParasolDto;
	
	/**
	 * 
	 * @param id
	 * @param numero
	 * @param prixJournalier
	 */
	public FileDto(Long id, byte numero,	double prixJournalier) {
		super();
		this.id = id;
		this.numero = numero;
		this.prixJournalier = prixJournalier;		
	}
	
	

}
