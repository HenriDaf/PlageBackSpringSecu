package com.projet.plage.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name="concessionnaire")
public class Concessionnaire extends Utilisateur {
	
//	/**
//	 * 
//	 */

		
	private static final long serialVersionUID = 1L;
	@NotNull(message="Merci de préciser le numéro de téléphone")
	@NotEmpty(message="Le numéro de téléphone ne peut pas être vide")
	//@Pattern(regexp="^(([+]\\d{2}|0)\\d{9})$", message="Le numéro de téléphone n'est pas valide")
	String numeroDeTelephone;
	
	
	

	
	
	public Concessionnaire(@NonNull String nom, @NonNull String prenom, @NonNull String email,
			@NonNull String password,@NotNull(message = "Merci de préciser le numéro de téléphone") 
	@NotEmpty(message = "Le numéro de téléphone ne peut pas être vide")  String numeroDeTelephone

		 ) {
		super(nom, prenom, email, password);
		this.numeroDeTelephone = numeroDeTelephone;

	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role.name()));
	}




	@Override
	public String getUsername() {

		return email;
	}




	@Override
	public boolean isAccountNonExpired() {

		return true;
	}




	@Override
	public boolean isAccountNonLocked() {

		return true;
	}




	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}




	@Override
	public boolean isEnabled() {

		return true;
	}


	

}
