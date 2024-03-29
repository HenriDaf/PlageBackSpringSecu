package com.projet.plage.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.plage.entity.Role;

import lombok.AccessLevel;

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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcessionnaireDto {

	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
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

	@Enumerated(EnumType.STRING)
	Role role;

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

	@Builder
	public ConcessionnaireDto(Long id, String nom,
			String prenom,
		    String email,
			String numeroDeTelephone,
			String password,
			Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numeroDeTelephone =numeroDeTelephone;
		this.password=getEncoder().encode(password);
		this.role=role;
	}

	
	
}
