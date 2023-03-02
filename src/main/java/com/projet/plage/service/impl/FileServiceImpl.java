package com.projet.plage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.plage.dao.FileDao;
import com.projet.plage.dto.FileDto;
import com.projet.plage.entity.File;
import com.projet.plage.mapper.FileMapper;
import com.projet.plage.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {
	
	@Autowired
	private FileDao fileDao;
	private FileMapper fileMapper;

	@Override
	public List<File> recupererListeFiles() {		
		return fileDao.findAll();
	}

	@Override
	public File ajouterFile(FileDto fileDto) {		
		File file = fileMapper.toEntity(fileDto);
		return enregistrerFile(file);
	}

	@Override
	public File modifierFile(FileDto fileDto) {
		File file = fileMapper.toEntity(fileDto);
		return enregistrerFile(file);
	}

	@Override
	public Boolean supprimerFile(Long id) {
		if (fileDao.existsById(id)) {
			fileDao.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public File enregistrerFile(File file) {
		return fileDao.save(file);
	}

	@Override
	public File trouverFileParId(Long id) {		
		return fileDao.findById(id).orElse(null);
	}

}
