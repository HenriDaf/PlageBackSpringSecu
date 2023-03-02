package com.projet.plage.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.plage.entity.Parasol;

public interface ParasolDao extends JpaRepository<Parasol, Long>{
	
	public Optional<Parasol>findById(Long id);
	
	
	/*@Query(
			""" 
            FROM parasol
            LEFT JOIN location ON parasol.id=location.id
            WHERE location.id IS NULL
            """			
    )
	List<Parasol> findParasolsNonReserve();*/
	
/*	@Query(
			""" 
            FROM parasol
            LEFT JOIN location_parasol ON parasol.id=liste_parasol_id
            WHERE locations.id IS NULL
            """			
    )
	List<Parasol> findParasolsNonReserve();*/
	
	@Query(value="SELECT * FROM parasol left join location_parasol AS lp ON parasol.id =lp.liste_parasol_id WHERE lp.locations_id IS NULL GROUP BY parasol.id, lp.locations_id, lp.liste_parasol_id",
		    nativeQuery=true)
		    List<Parasol> findParasolsNonReserve();	

}
 