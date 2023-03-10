package com.projet.plage.dto;

import java.util.Collection;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import com.projet.plage.entity.LienDeParente;
import com.projet.plage.entity.Pays;
import com.projet.plage.entity.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class LocataireDto {

	
	Long id;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner un nom")
	String nom;

	@NonNull
	@NotBlank(message = "Veuillez renseigner un prenom")
	String prenom;

	@NonNull
	@NotBlank(message = "Veuillez renseigner une adresse mail")	
	@Pattern(regexp="^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*@orsys.fr$", message="Votre adresse doit se terminer par @orsys.fr")
	String email;

	@NonNull
	@NotBlank(message = "Veuillez renseigner un mot de passe")
	@Size(min=8, message = "Le mot de passe doit contenir au minimum 8 caractères")
	String password;

	
	//LienDeParenteDto lienDeParenteDto;
	@NonNull
	LienDeParente lienDeParente;

	@NonNull
	Pays pays;
	//PaysDto paysDto;
	
	//ajout pour la partie location	
	
	@Enumerated(EnumType.STRING)
	Role role;
	
	/**
	 * Constructeur pour la partie location
	 */
	List<LocationDto> listeLocationDto;
	

	public LocataireDto(Long id, String nom,
			 String prenom,
			 String email,
			 LienDeParente lienDeParente,
			 Pays pays) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	
		this.lienDeParente=lienDeParente;
				this.pays=pays;
	}
	@Builder
	public LocataireDto(Long id, String nom,
			String prenom,
			String email,
			String password,
			LienDeParente lienDeParente,
			Pays pays,
			Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password=password;
		this.lienDeParente=lienDeParente;
		this.pays=pays;
		this.role=role;
	}


}
