package com.projet.plage.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.plage.entity.File;

public interface FileDao extends JpaRepository<File, Long> {
	
	public Optional<File>findById(Long id);

}
