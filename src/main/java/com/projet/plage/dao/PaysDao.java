package com.projet.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.plage.entity.Pays;

public interface PaysDao extends JpaRepository<Pays, String> {
	
	
	Pays findByCode(String code);
	
	Pays findByNom(String nom);

}
