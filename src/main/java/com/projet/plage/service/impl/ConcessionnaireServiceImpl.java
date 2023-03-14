package com.projet.plage.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.exception.UtilisateurDejaExistantException;
import com.projet.plage.mapper.ConcessionnaireMapper;
import com.projet.plage.service.IConcessionnaireService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConcessionnaireServiceImpl implements IConcessionnaireService {

	private ConcessionnaireDao concessionnaireDao;

	
	private ConcessionnaireMapper concessionnaireMapper;

	@Override
	public List<Concessionnaire> recupererConcessionnaire() {

		return concessionnaireDao.findAll();
	}

	@Override
	public Concessionnaire recupererParId(Long id) {

		return concessionnaireDao.findById(id).orElse(null);
	}

	@Override
	public Concessionnaire recupererParEmail(String email) {
		return concessionnaireDao.findByEmail(email);
	}

	@Override
	public Concessionnaire ajouterConcessionnaire(ConcessionnaireDto concessionnaireDto) {
		if (concessionnaireDto != null && concessionnaireDao.findByEmail(concessionnaireDto.getEmail()) == null) {
			Concessionnaire concessionnaire = concessionnaireMapper.toEntity(concessionnaireDto);

				return concessionnaireDao.save(concessionnaire);
			

		} else {
			throw new UtilisateurDejaExistantException(
					"Cette adresse email existe déja, veuillez en choisir une autre");
		}
		// return concessionnaireDao.save(concessionnaireDto);

	}

	@Override
	public Concessionnaire modifierConcessionnaire(Concessionnaire concessionnaire) {
		return concessionnaireDao.save(concessionnaire);
	}

	@Override
	public Boolean supprimerConcessionnaire(String email) {
		Concessionnaire concessionnaire = recupererParEmail(email);

		if (concessionnaire != null) {
			try {
				concessionnaireDao.delete(concessionnaire);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}

	

}
