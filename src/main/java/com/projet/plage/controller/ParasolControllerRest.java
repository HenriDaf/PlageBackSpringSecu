package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.plage.dao.ParasolDao;
import com.projet.plage.entity.Parasol;
import com.projet.plage.service.IParasolService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class ParasolControllerRest {
	
	private final IParasolService parasolService;
	private final ParasolDao parasolDao;
	
	@GetMapping("/parasols")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Parasol>> listeParasols(){
        List<Parasol> parasols = parasolService.recupererListeParasols();

        if(parasols.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(parasols);
    }
	
	@GetMapping("parasols/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public ResponseEntity<Parasol> recupererParasolParId(@PathVariable("id") Long id){		
	Parasol parasol =  parasolService.trouverParasolParId(id);
	
	if(parasol == null) {
		return ResponseEntity.noContent().build();
	}
	return ResponseEntity.ok(parasol);
	}

	
	@GetMapping("parasolsNonReserves")
	public ResponseEntity<List<Parasol>> recupererParasolNonReserves(){
		
		
		 List<Parasol> parasols=  parasolDao.findParasolsNonReserve();
	
		
		 return ResponseEntity.ok(parasols);
		
	}
}
