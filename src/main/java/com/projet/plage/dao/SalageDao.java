package com.projet.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.plage.entity.Salage;

public interface SalageDao extends JpaRepository<Salage, Long>{
	
	@Query(value="SELECT sel FROM Salage WHERE id=:idUtilisateur", nativeQuery=true)
	public byte[] findSelByUtilisateur(@Param("idUtilisateur")Long id);
	

}
