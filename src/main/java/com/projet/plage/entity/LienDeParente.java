package com.projet.plage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import org.hibernate.validator.constraints.Range;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="lienDeParente")
public class LienDeParente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;	
	
	@NotBlank(message = "Veuillez renseigner un nom")
	@NotNull(message="Merci de préciser le nom")
	String nom;
	
	@NotNull(message="Merci de préciser le coéfficient")
	//@Range(min=0, max=1, message="Le coéfficient est compris entre 0 et 1")
	float coefficient;	


	public LienDeParente(
			@NotBlank(message = "Veuillez renseigner un nom") @NotNull(message = "Merci de préciser le nom") String nom,
			@NotNull(message = "Merci de préciser le coéfficient") float coefficient) {
		super();
		this.nom = nom;
		this.coefficient = coefficient;
	}	
	
	
	

	
}

