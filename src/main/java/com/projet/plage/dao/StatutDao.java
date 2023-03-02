package com.projet.plage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.plage.entity.Statut;

public interface StatutDao extends JpaRepository<Statut, Long> {
	
	public Optional<Statut> findById(Long id);
	
	Statut findByNom(String nom);
}
