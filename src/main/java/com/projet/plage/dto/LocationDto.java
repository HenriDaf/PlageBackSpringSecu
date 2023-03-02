package com.projet.plage.dto;


import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.projet.plage.entity.Concessionnaire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {	
	
	@FutureOrPresent(message="La date doit être aujourd’hui ou dans le futur")
	private LocalDateTime dateHeureDebut;		
	
	@FutureOrPresent(message="La date doit être aujourd’hui ou dans le futur")
	private LocalDateTime dateHeureFin;		
	
	@Positive(message="Merci de préciser un nombre strictement positif")
	@Min(value=15, message="Le prix ne peut pas être inférieur à 15 euros")
	private double montantAReglerEnEuros;	
	
	private String remarques;	
		
	@NotNull(message="Le champ ne peut pas être null")
	private LocataireDto locataireDto;	
	
	private Long idLocataire;
		
	@NotNull(message="Le champ ne peut pas être null")
	@NotEmpty(message="La liste des parasols ne peut pas être vide")
	private List<ParasolDto> listeParasolDto;
			
	@NotNull(message="Le champ ne peut pas être null")
	private StatutDto statutDto;
	
	private Concessionnaire concessionnaire;

	public LocationDto(
			LocalDateTime dateHeureDebut,
			LocalDateTime dateHeureFin,
			double montantAReglerEnEuros,
			String remarques,
			Long idLocataire,
			List<ParasolDto> listeParasolDto,
			StatutDto statutDto) {
		super();
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.montantAReglerEnEuros = montantAReglerEnEuros;
		this.remarques = remarques;
		this.idLocataire = idLocataire;
		this.listeParasolDto = listeParasolDto;
		this.statutDto = statutDto;
	}	
	
	
}
