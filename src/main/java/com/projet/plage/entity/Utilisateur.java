package com.projet.plage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor

@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@Table(name="utilisateur")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur implements UserDetails { 	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NonNull
	@NotBlank(message = "Veuillez renseigner un nom")
	String nom;
	
	@NonNull
	@NotBlank(message = "Veuillez renseigner un prenom")
	String prenom;

	@NonNull
	@NotBlank(message = "Veuillez renseigner une adresse mail")
	@Column(name="email", unique=true)
	@Pattern(regexp="^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*@orsys.fr$", message="Votre adresse doit se terminer par @orsys.fr")
	String email;

	@NonNull
	@NotBlank(message = "Veuillez renseigner un mot de passe")
	@Size(min=8, message = "Le mot de passe doit contenir au minimum 8 caractères")
	String password;
	
	
	/*@NonNull
	byte[] sel;*/
	
	@NonNull
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="salage_id", referencedColumnName = "id")
	Salage salage;
	
	@Enumerated(EnumType.STRING)
	Role role;

}
