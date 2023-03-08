package com.projet.plage.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConcessionnaireInscriptionRequest {

	
	String nom;
	String prenom;
	String email;
	String password;
	String numeroDeTelephone;
}
