package com.projet.plage.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.plage.entity.Locataire;


public interface LocataireDao extends JpaRepository<Locataire, Long> {
	
	public Locataire findByEmail(String email);
	
	public Locataire findByEmailAndPassword(String email, String password);	
	
	
	@Query(value="SELECT sel FROM locataire WHERE email=:locataireEmail", nativeQuery=true)
	public byte[] findSelByEmail(@Param("locataireEmail") String email);
	
//	@Query(value="SELECT FROM locataire WHERE email=:locataireEmail", nativeQuery=true)
//	public byte[] findHashByEmail(@Param("locataireEmail") String email);
//	
	

	@Query(value="SELECT id FROM locataire WHERE email=:locataireEmail", nativeQuery=true)
	public Long findIdByEmail(@Param("locataireEmail") String email);
	
}
