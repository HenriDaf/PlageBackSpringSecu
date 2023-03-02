package com.projet.plage.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="parasol")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = false)
public class Parasol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Merci de préciser le numéro d'emplacement")
	@Min(value=1, message="Le numéro doit être supérieur ou égal à 1")
	@Max(value=9, message="Le numéro doit être inférieur ou égal à 9")
	private byte numEmplacement;
		
	@ManyToOne
	@JoinColumn(name="id_file")	
	@ToString.Exclude
	private File file;	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "listeParasol", fetch=FetchType.LAZY)	
	@ToString.Exclude
	private List<Location> locations;

	public Parasol(
			@NotNull(message = "Merci de préciser le numéro d'emplacement") @Min(value = 1, message = "Le numéro doit être supérieur ou égal à 1") @Max(value = 9, message = "Le numéro doit être inférieur ou égal à 9") byte numEmplacement,
			//@NotNull(message = "Le champ ne peut pas être null")
			File file) {
		super();
		this.numEmplacement = numEmplacement;
		this.file = file;
		
	}	
	
	
	
	
}
