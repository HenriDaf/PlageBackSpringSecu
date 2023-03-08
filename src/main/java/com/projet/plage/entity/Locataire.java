package com.projet.plage.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

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
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name="locataire")
public class Locataire extends Utilisateur {

	

	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)
	LocalDateTime dateHeureInscription=LocalDateTime.now();
	
	@OneToMany(mappedBy="locataire", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Location> listeLocation;
	
	@ManyToOne
	@JoinColumn(name="id_lienDeParente")
	//@NotBlank(message = "Veuillez renseigner un lien de parenté")
	@NonNull
	@ToString.Exclude
	LienDeParente lienDeParente;
	
	@ManyToOne
	@JoinColumn(name="id_pays")
	//@NotBlank(message = "Veuillez renseigner un pays")
	@NonNull
	@ToString.Exclude
	Pays pays;

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
