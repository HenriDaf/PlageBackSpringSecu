package com.projet.plage.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="pays")
public class Pays {

	@Id	
	@NonNull
	@NotBlank(message = "Veuillez renseigner un code")
	String code;
		
	@NonNull
	@NotBlank(message = "Veuillez renseigner un nom de pays")
	String nom;
	
	@JsonIgnore
	//@OneToMany(mappedBy = "pays", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "pays", cascade = CascadeType.REMOVE)
	List<Locataire> locataires;

}
