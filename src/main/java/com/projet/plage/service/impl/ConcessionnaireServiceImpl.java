package com.projet.plage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dao.SalageDao;
import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.entity.Salage;
import com.projet.plage.exception.EchecCreationUtilisateurException;
import com.projet.plage.exception.UtilisateurDejaExistantException;
import com.projet.plage.exception.UtilisateurNonTrouveException;
import com.projet.plage.mapper.ConcessionnaireMapper;
import com.projet.plage.service.IChiffrageService;
import com.projet.plage.service.IConcessionnaireService;
import com.projet.plage.service.ISalageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConcessionnaireServiceImpl implements IConcessionnaireService {

	private ConcessionnaireDao concessionnaireDao;
	private SalageDao salageDao;
	private IChiffrageService iChiffrageService;
	private ISalageService iSalageService;
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

			ArrayList<Object> info = (iChiffrageService.genererChiffrage(concessionnaire.getPassword()));

			if (info.size() == 2) {
				Salage salage = new Salage((byte[]) info.get(1), concessionnaire);
				iSalageService.ajouterSalage(salage);
				concessionnaire.setSalage(salage);
				concessionnaire.setPassword(info.get(0).toString());

				return concessionnaireDao.save(concessionnaire);
			} else {
				throw new EchecCreationUtilisateurException(
						"Echec lors de la création de l'utilisateur, veuillez recommencer ultérieurement");
			}

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

	@Override
	public Concessionnaire authentifierConcessionnaireParEmailMotDePasse(String email, String password) {

		Long idConcessionnaire = concessionnaireDao.findIdByEmail(email);
		byte[] sel = salageDao.findSelByUtilisateur(idConcessionnaire);

		if (sel != null) {
			String mdp = iChiffrageService.validitePassword(password, sel);

			if (concessionnaireDao.findByEmailAndPassword(email, mdp) != null) {
				return concessionnaireDao.findByEmailAndPassword(email, mdp);
			} else {
				throw new UtilisateurNonTrouveException("Mot de passe et ou adresse email invalide");
			}
		} else {
			throw new UtilisateurNonTrouveException("Mot de passe et ou adresse email invalide");
		}

	}

}
