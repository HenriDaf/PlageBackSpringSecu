package com.projet.plage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="statut")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = false)
public class Statut {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Veuillez renseigner un statut")
	@NotNull(message="Merci de préciser le nom du statut")
	private String nom;

	public Statut( String nom) {
		super();
		this.nom = nom;
	}
	
}
