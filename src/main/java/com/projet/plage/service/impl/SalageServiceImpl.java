package com.projet.plage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.plage.dao.SalageDao;
import com.projet.plage.entity.Salage;
import com.projet.plage.service.ISalageService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SalageServiceImpl implements ISalageService {

	SalageDao salageDao;
	
	@Override
	public Salage ajouterSalage(Salage salage) {
		
		if(salage != null) {
			return salageDao.save(salage);
			
		}else {
			return null;
		}
	}

	@Override
	public Boolean supprimerSalage(Long id) {
		// TODO Auto-generated method stub
		 try{
			 salageDao.deleteById(id);
			 return true;
		 }catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Salage> recupererListe() {
		return salageDao.findAll();
	}

}
