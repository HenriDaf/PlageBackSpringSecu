package com.projet.plage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.plage.dao.StatutDao;
import com.projet.plage.dto.StatutDto;
import com.projet.plage.entity.Statut;
import com.projet.plage.mapper.StatutMapper;
import com.projet.plage.service.IStatutService;

@Service
public class StatutServiceImpl implements IStatutService {
	
	@Autowired
	private StatutDao statutDao;
	private StatutMapper statutMapper;

	@Override
	public List<Statut> recupererStatuts() {		
		return statutDao.findAll();
	}

	@Override
	public Statut recupererStatutParNom(String nom) {		
		return statutDao.findByNom(nom);
	}

	@Override
	public Statut ajouterStatuts(StatutDto statutDto) {		
		Statut statut = statutMapper.toEntity(statutDto);
		return enregistrerStatut(statut);
	}
	
	@Override
	public Statut enregistrerStatut(Statut statut) {
		return statutDao.save(statut);
	}

	@Override
	public Statut modifierStatut(StatutDto statutDto) {		
		Statut statut = statutMapper.toEntity(statutDto);
		return enregistrerStatut(statut);
	}

	@Override
	public Boolean supprimerStatut(Long id) {
		if (statutDao.existsById(id)) {
			statutDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Statut recupererStatutParId(Long id) {		
		return statutDao.findById(id).orElse(null);
	}
}
