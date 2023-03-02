package com.projet.plage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import lombok.AccessLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter

@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcessionnaireDto {

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
	
	
	@NonNull
	@NotEmpty(message="Le numéro de téléphone ne peut pas être vide")
	String numeroDeTelephone;



	public ConcessionnaireDto(Long id, @NonNull @NotBlank(message = "Veuillez renseigner un nom") String nom,
			@NonNull @NotBlank(message = "Veuillez renseigner un prenom") String prenom,
			@NonNull @NotBlank(message = "Veuillez renseigner une adresse mail") @Pattern(regexp = "^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*@orsys.fr$", message = "Votre adresse doit se terminer par @orsys.fr") String email,
			@NotNull(message = "Merci de préciser le numéro de téléphone") @NotEmpty(message = "Le numéro de téléphone ne peut pas être vide") String numeroDeTelephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numeroDeTelephone = numeroDeTelephone;
	}

	
	
}
