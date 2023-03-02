package com.projet.plage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.plage.entity.Concessionnaire;



/**
 *  Interface dao du concessionnaire, avec notamment une requête par dérivation(findByEmailAndPassword()),
  nous permettant de gérer l'authentification d'un concessionnaire. Cette méthode sera utilisée dans le service Concessionaire.

 */
  
 

public interface ConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

	
	Concessionnaire findByEmailAndPassword(String email, String password);	
	
	public Concessionnaire findByEmail(String email);
	
	public Optional<Concessionnaire> findById(Long id);	
	
	@Query(value="SELECT sel FROM concessionnaire WHERE email=:concessionnaireEmail", nativeQuery=true)
	public byte[] findSelByEmail(@Param("concessionnaireEmail") String email);
	
	@Query(value="SELECT id FROM concessionnaire WHERE email=:concessionnaireEmail", nativeQuery=true)
	public Long findIdByEmail(@Param("concessionnaireEmail") String email);
	
}
