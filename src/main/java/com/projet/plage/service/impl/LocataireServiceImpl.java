package com.projet.plage.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.projet.plage.dao.LocataireDao;
import com.projet.plage.dao.LocationDao;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Location;
import com.projet.plage.exception.UtilisateurDejaExistantException;
import com.projet.plage.exception.UtilisateurNonSupprimable;
import com.projet.plage.exception.UtilisateurNonTrouveException;
import com.projet.plage.mapper.LocataireMapper;
import com.projet.plage.service.ILocataireService;

import java.util.Collections;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocataireServiceImpl implements ILocataireService {

	private LocataireDao locataireDao;
	private LocataireMapper locataireMapper;
	private LocationDao locationDao;

	

	@Override
	public List<Locataire> recupererLocataire() {

		return locataireDao.findAll();
	}

	@Override
	public Locataire recupererLocataireParId(Long id) {

		return locataireDao.findById(id).orElse(null);
	}

	@Override
	public Locataire recupererLocataireParEmail(String email) {
		return locataireDao.findByEmail(email);
	}



	@Override
	public Locataire ajouterLocataire(LocataireDto locataireDto) {
		//System.out.println("locataireDTO "+locataireDto);

		if (locataireDto != null && locataireDao.findByEmail(locataireDto.getEmail()) == null) {
			Locataire locataire=locataireMapper.toEntity(locataireDto);
			return locataireDao.save(locataire);
		} else {
			throw new UtilisateurDejaExistantException(
					"Cette adresse email existe déja, veuillez en choisir une autre");
		}
	}

	@Override
	public Locataire ajouterLocataire(Locataire locataire) {

		if (locataire != null) {
			return locataireDao.save((locataire));
		} else {
			return null;
		}
	}

	@Override
	public List<Location> recupererLesLocationsParLocataire(Long id) {

		Locataire locataire = recupererLocataireParId(id);
		if (locataire != null) {
			return locationDao.findAllByLocataire(locataire);

		}
		return Collections.emptyList();
	}

	@Override
	public Locataire modifierLocataire(LocataireDto locataireDto) {
		if (locataireDto != null) {
			return locataireDao.save(locataireMapper.toEntity(locataireDto));
		} else {
			return null;
		}
	}

	@Override
	public Boolean supprimerLocataire(String email) {
		Locataire locataire= recupererLocataireParEmail(email);

		boolean supprimable=true;

		if(locataire!=null ) {
			try {

				List<Location>locations = locataire.getListeLocation();

				for(Location location:locations) {
					if("confirmée".equals(location.getStatut().getNom())) {
						supprimable=false;
						break;
					}
				}
				if(supprimable) {
					System.out.println(locataire);
					locataireDao.delete(locataire);
					return true;

				}else {
					throw new UtilisateurNonSupprimable("Cet utilisateur possède une réservation déja validée, vous ne pouvez pas le supprimer");
				}

			}catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public Boolean supprimerLocataire(Long id) {
		Locataire locataire = recupererLocataireParId(id);
System.out.println(locataire);
		boolean supprimable=true;

		if(locataire!=null ) {
			try {

				List<Location>locations = locataire.getListeLocation();
System.out.println(locations);
				for(Location location:locations) {
					if("confirmée".equals(location.getStatut().getNom())) {
						supprimable=false;
						break;
					}
				}
				if(supprimable) {
					//salageDao.delete(locataire.getSalage());
					locataireDao.delete(locataire);
					return true;

				}else {
					throw new UtilisateurNonSupprimable("Cet utilisateur possède une réservation déja validée, vous ne pouvez pas le supprimer");
				}

			}catch (Exception e) {
				throw new UtilisateurNonSupprimable("Cet utilisateur possède une réservation déja validée, vous ne pouvez pas le supprimer");
				
			}
		}
		
		throw new UtilisateurNonTrouveException("Utilisateur non existant");
		// return false;//throw exception utilisateur non trouve
	}

}
