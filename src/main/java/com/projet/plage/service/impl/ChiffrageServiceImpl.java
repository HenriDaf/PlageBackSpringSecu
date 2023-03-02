package com.projet.plage.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;


import org.springframework.stereotype.Service;

import com.projet.plage.service.IChiffrageService;

@Service
public class ChiffrageServiceImpl implements IChiffrageService {

	@Override
	public ArrayList<Object>  genererChiffrage(String motDePasse) {

		
		ArrayList<Object> liste=new ArrayList<>();
		String mdp = motDePasse;

		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-256");
			
			byte[]salt= getSalt();
			msg.update(salt);
			byte[] hash = msg.digest(mdp.getBytes(StandardCharsets.UTF_8));

			StringBuilder s = new StringBuilder();
			for (byte b : hash) {
				s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			
			
			liste.add(0, s);
			liste.add(1,salt);
			return liste;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public byte[] getSalt() {

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	@Override
	public String validitePassword(String mdp, byte[]sel) {
		
		MessageDigest msg;
		try {
			msg = MessageDigest.getInstance("SHA-256");
			msg.update(sel);
			byte[] hash = msg.digest(mdp.getBytes(StandardCharsets.UTF_8));

			StringBuilder s = new StringBuilder();
			for (byte b : hash) {
				s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			System.out.println(s.toString());
			return s.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
