package com.projet.plage.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "file")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = false)
public class File {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Merci de préciser le numéro de file")
	@Min(value=1, message="Le numéro doit être supérieur ou égal à 1")
	@Max(value=8, message="Le numéro doit être inférieur ou égal à 8")
	private byte numero;
	
	@NotNull(message="Merci de préciser le prix")
	@Positive(message="Merci de préciser un nombre strictement positif")
	@Min(value=15, message="Le prix doit être supérieur ou égal à 15 euros")
	private double prixJournalier;
	
	@JsonIgnore
	@OneToMany(mappedBy="file", fetch=FetchType.LAZY)
	private List<Parasol> listeParasol;

	public File(
			@NotNull(message = "Merci de préciser le numéro de file") @Min(value = 1, message = "Le numéro doit être supérieur ou égal à 1") @Max(value = 8, message = "Le numéro doit être inférieur ou égal à 8") byte numero,
			@NotNull(message = "Merci de préciser le prix") @Positive(message = "Merci de préciser un nombre strictement positif") @Min(value = 15, message = "Le prix doit être supérieur ou égal à 15 euros") double prixJournalier,
		@Lazy List<Parasol> listeParasol) {
		super();
		this.numero = numero;
		this.prixJournalier = prixJournalier;
		this.listeParasol = listeParasol;
	}
	
	
	
}
