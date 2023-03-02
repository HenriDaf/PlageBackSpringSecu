package com.projet.plage.service;

import java.util.ArrayList;

public interface IChiffrageService {

	ArrayList<Object> genererChiffrage(String motDePasse);

	byte[] getSalt();

	
	String validitePassword(String password, byte[]sel);
}
