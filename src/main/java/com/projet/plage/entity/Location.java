package com.projet.plage.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "location")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString()

public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	@FutureOrPresent(message="La date doit être aujourd’hui ou dans le futur")
	private LocalDateTime dateHeureDebut;	
	
	@FutureOrPresent(message="La date doit être aujourd’hui ou dans le futur")
	private LocalDateTime dateHeureFin;	
	
	@Positive(message="Merci de préciser un nombre strictement positif")
	@Min(value=15, message="Le prix ne peut pas être inférieur à 15 euros")
	private double montantAReglerEnEuros;	
	
	private String remarques;	

	@ManyToOne
	@JoinColumn(name="id_locataire")
	@ToString.Exclude
	//@NotNull(message="Le champ ne peut pas être null")
	private Locataire locataire;
	
	@ManyToMany	
	@ToString.Exclude
	@JoinTable(name = "location_parasol")
	//@NotNull(message="Le champ ne peut pas être null")
	@NotEmpty(message="La liste des parasols ne peut pas être vide")
	private List<Parasol> listeParasol;
	
	@ManyToOne
	@JoinColumn(name="id_statut")
	//@NotNull(message="Le champ ne peut pas être null statut")
	private Statut statut;
	
	@JoinColumn(name="id_concessionnaire")
	@ManyToOne
	@NotNull(message="Le champ ne peut pas être null")
	@JsonIgnore
	private Concessionnaire concessionnaire;
	
	/**
	 * Constructeur partie location
	 */
	public Location(
			 LocalDateTime dateHeureDebut,
			LocalDateTime dateHeureFin,
			double montantAReglerEnEuros,
			String remarques,
			Locataire locataire,
			List<Parasol> listeParasol,
			Statut statut,
			Concessionnaire concessionnaire) {
		super();
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.montantAReglerEnEuros = montantAReglerEnEuros;
		this.remarques = remarques;
		this.locataire = locataire;
		this.listeParasol = listeParasol;
		this.statut = statut;
		this.concessionnaire = concessionnaire;
	}

	

}
