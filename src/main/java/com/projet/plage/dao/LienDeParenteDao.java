package com.projet.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.plage.entity.LienDeParente;

public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {
	
	LienDeParente findByNom(String nom);

}
