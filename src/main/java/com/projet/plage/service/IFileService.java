package com.projet.plage.service;

import java.util.List;

import com.projet.plage.dto.FileDto;
import com.projet.plage.entity.File;
import com.projet.plage.entity.Statut;

public interface IFileService {
	
	List <File> recupererListeFiles();		
	
	File ajouterFile(FileDto fileDto);
	 
	 File enregistrerFile(File file);
	 
	 File modifierFile(FileDto fileDto);
	 
	 Boolean supprimerFile(Long id);
	 
	 File trouverFileParId(Long id);

}
