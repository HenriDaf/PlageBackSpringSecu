package com.projet.plage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.plage.dao.PaysDao;
import com.projet.plage.dto.PaysDto;
import com.projet.plage.entity.Pays;
import com.projet.plage.mapper.PaysMapper;
import com.projet.plage.service.IPaysService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PaysServiceImpl implements IPaysService {
	
	private PaysDao paysDao;
	private PaysMapper paysMapper;

	@Override
	public List<Pays> recupererPays() {

		
		return paysDao.findAll();
	}

	@Override
	public Pays recupererPaysParCode(String code) {

		return paysDao.findByCode(code);
	}

	@Override
	public Pays recupererPaysParNom(String nom) {

		return paysDao.findByNom(nom);
	}

	@Override
	public Pays ajouterPays(PaysDto paysDto) {
		
		if(paysDto!=null) {
			
			return paysDao.save(paysMapper.toEntity(paysDto));
		}else {
			return null; // ici mettre une exception?
		}

	}

	@Override
	public Pays modifierPays(PaysDto paysDto) {

		return paysMapper.toEntity(paysDto);
	}

	@Override
	public Boolean supprimerPays(String code) {
		Pays pays= recupererPaysParCode(code);
		if(pays!=null) {
			try {
				paysDao.delete(pays);
				return true;	
			}catch (Exception e) {
				return false;
			}
			
		}
		return false;
		
	}

}
