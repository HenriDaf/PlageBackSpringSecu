package com.projet.plage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.plage.dao.ParasolDao;
import com.projet.plage.dto.ParasolDto;
import com.projet.plage.entity.Parasol;
import com.projet.plage.mapper.ParasolMapper;
import com.projet.plage.service.IParasolService;

@Service
public class ParasolServiceImpl implements IParasolService {
	
	@Autowired
	private ParasolDao parasolDao;
	private ParasolMapper parasolMapper;
	
	@Override
	public List<Parasol> recupererListeParasols() {
		return parasolDao.findAll();
	}
	@Override
	public Parasol ajouterParasol(ParasolDto parasolDto) {
		Parasol parasol = parasolMapper.toEntity(parasolDto);
		return enregistrerParasol(parasol);
	}
	@Override
	public Parasol enregistrerParasol(Parasol parasol) {		
		return parasolDao.save(parasol);
	}
	@Override
	public Parasol modifierParasol(ParasolDto parasolDto) {
		Parasol parasol = parasolMapper.toEntity(parasolDto);
		return enregistrerParasol(parasol);
	}
	@Override
	public Boolean supprimerParasol(Long id) {
		if (parasolDao.existsById(id)) {
			parasolDao.deleteById(id);
			return true;
		}
		return false;	
	}
	@Override
	public Parasol trouverParasolParId(Long id) {		
		return parasolDao.findById(id).orElse(null);
	}
	
	

}
