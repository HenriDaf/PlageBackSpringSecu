package com.projet.plage.initialisation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dao.FileDao;
import com.projet.plage.dao.LienDeParenteDao;
import com.projet.plage.dao.LocataireDao;
import com.projet.plage.dao.ParasolDao;
import com.projet.plage.dao.PaysDao;
import com.projet.plage.dao.StatutDao;
import com.projet.plage.dto.ConcessionnaireDto;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.File;
import com.projet.plage.entity.LienDeParente;
import com.projet.plage.entity.Parasol;
import com.projet.plage.entity.Pays;
import com.projet.plage.entity.Statut;
import com.projet.plage.service.IConcessionnaireService;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.IPaysService;

import lombok.AllArgsConstructor;

/**
 * 
 * @author Henri.
 * 
 * 
 *         La classe AjoutDonneesInitiales implémentant CommandLineRunner, est
 *         utilisée pour insérer des données en base(si les différentes tables
 *         sont vides), au lancement de l'application.
 * 
 * 
 * 
 *         On insère en base 5 pays, 3 liens de parentés, 1 concessionnaire, 8
 *         files et 72 parasols.
 * 
 *         Pour cela on injecte via le constructeur (@AllArgsConstructor) les
 *         dao respectives.
 *
 */

@AllArgsConstructor
@Component
public class AjoutDonneesInitiales implements CommandLineRunner {

	PaysDao paysDao;
	LienDeParenteDao lienDeParenteDao;

	StatutDao statutDao;
	FileDao fileDao;

	ConcessionnaireDao concessionnaireDao;
	IConcessionnaireService iConcessionnaireService;

	ParasolDao parasolDao;

	LocataireDao locataireDao;
	ILocataireService iLocataireService;
	ILienDeParenteService iLienDeParenteService;
	IPaysService iPaysService;

	@Override
	public void run(String... args) throws Exception {

		ajouterPays();
		ajouterLienDeParente();
		ajouterConcessionnaire();
		ajouterDesStatuts();
		ajouterDesFiles();
		ajouterDesParasols();
		ajouterLocataire();

	}

	public void ajouterPays() {

		if (paysDao.count() == 0) {

			paysDao.save((new Pays("FRA", "France")));
			paysDao.save((new Pays("IE", "Irlande")));
			paysDao.save((new Pays("IT", "Italie")));
			paysDao.save((new Pays("AU", "Australie")));
			paysDao.save((new Pays("US", "Etats-Unis")));
		}
	}

	public void ajouterLienDeParente() {
		if (lienDeParenteDao.count() == 0) {
			lienDeParenteDao.save(new LienDeParente("frère/soeur", 0.50f));
			lienDeParenteDao.save(new LienDeParente("cousin/cousine", 0.25f));
			lienDeParenteDao.save(new LienDeParente("aucun", 1f));

		}

	}

	public void ajouterDesStatuts() {
		if (statutDao.count() == 0) {

			statutDao.save((new Statut("à traiter")));
			statutDao.save((new Statut("confirmée")));
			statutDao.save((new Statut("refusée")));
		}
	}

	public void ajouterDesFiles() {
		if (fileDao.count() == 0) {

			int count = 1;
			double prixjournalier = 50;

			for (int i = 1; i < 9; i++) {
				File file = new File((byte) count, prixjournalier - (i * 4), null);
				count++;
				/*
				 * file.setNumero((byte)count); file.setListeParasol(null);
				 * 
				 * file.setPrixJournalier(20);
				 */
				fileDao.save(file);
			}
		}

	}

	public void ajouterDesParasols() {
		if (parasolDao.count() == 0) {

			Long count = (long) 1;
			while (count <= 8) {

				for (int i = 1; i < 10; i++) {

					Parasol parasol = new Parasol((byte) i, fileDao.findById(count).orElse(null));

					if (i % 9 == 0) {
						count++;
					}

					parasolDao.save(parasol);
				}
			}

		}

	}

	public void ajouterConcessionnaire() {
		if (concessionnaireDao.count() == 0) {
			concessionnaireDao.save(iConcessionnaireService.ajouterConcessionnaire(
					new ConcessionnaireDto("Doe", "John", "peppe@orsys.fr", "12345678", "+3912345678")));

		}
	}

	public void ajouterLocataire() {
		if (locataireDao.count() == 0) {
			locataireDao.save(iLocataireService.ajouterLocataire(
					new LocataireDto("test", "test", "test@orsys.fr", "12345678", iLienDeParenteService.recupererLienDeParenteParNom("aucun"), iPaysService.recupererPaysParNom("France") )));

		}
	}

}
