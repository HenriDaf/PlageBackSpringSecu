package com.projet.plage.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.projet.plage.dao.LienDeParenteDao;
import com.projet.plage.dto.LienDeParenteDto;
import com.projet.plage.entity.LienDeParente;
import com.projet.plage.exception.LienDeParenteNonTrouve;
import com.projet.plage.mapper.LienDeParenteMapper;
import com.projet.plage.service.ILienDeParenteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LienDeParenteServiceImpl implements ILienDeParenteService {

	private LienDeParenteDao lienDeParenteDao;

	private LienDeParenteMapper lienDeParenteMapper;

	@Override
	public List<LienDeParente> recupererLienDeParente() {

		return lienDeParenteDao.findAll();
	}

	@Override
	public LienDeParente ajouterLienDeParente(LienDeParenteDto lienDeParenteDto) {

		return lienDeParenteMapper.toEntity(lienDeParenteDto);
	}

	@Override
	public LienDeParente modifiLienDeParente(LienDeParenteDto lienDeParenteDto) {

		return lienDeParenteMapper.toEntity(lienDeParenteDto);
	}

	@Override
	public Boolean supprimerLienDeParenteParId(Long id) {
		LienDeParente lienDeParente = lienDeParenteDao.findById(id).orElse(null);
		if (lienDeParente != null) {
			try {
				lienDeParenteDao.delete(lienDeParente);
				return true;
			} catch (Exception e) {
				return false;
			}

		}
		return false;
	}

	@Override
	public Boolean supprimerLienDeParenteParNom(String nom) {
		LienDeParente lienDeParente = lienDeParenteDao.findByNom(nom);

		if (lienDeParente != null) {
			try {
				lienDeParenteDao.delete(lienDeParente);
				return true;
			} catch (Exception e) {
				return false;
			}

		}
		return false;
	}

	@Override
	public LienDeParente recupererLienDeParenteParNom(String nom) {
		LienDeParente lienDeParente = lienDeParenteDao.findByNom(nom);
		if (lienDeParente != null) {
			return lienDeParente;
		} else {
			throw new LienDeParenteNonTrouve("Lien de parenté introuvable");
		}

	}
}