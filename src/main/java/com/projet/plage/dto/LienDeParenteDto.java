package com.projet.plage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LienDeParenteDto {
	
	@NotBlank(message = "Veuillez renseigner un nom")
	@NotNull(message="Merci de préciser le nom")
	@NonNull
	String nom;
	
	@NotNull(message="Merci de préciser le coéfficient")
	
	@Pattern(regexp="1|(0.25)|(0.50)", message="Le coéfficient est soit 1 ou 0.25 ou 0.50")
	float coefficient;	

}
