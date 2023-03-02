package com.projet.plage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.plage.entity.File;
import com.projet.plage.service.IFileService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class FileControllerRest {
	
	private final IFileService fileService;
	
	
	@GetMapping("/files")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<File>> listeFiles(){
        List<File> files = fileService.recupererListeFiles();

        if(files.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(files);
    }
	
	@GetMapping("files/{id}")
	@ResponseStatus(code=HttpStatus.OK)
	public ResponseEntity<File> recupererFileParId(@PathVariable("id") Long id){		
	File file =  fileService.trouverFileParId(id);
	
	if(file == null) {
		return ResponseEntity.noContent().build();
	}
	return ResponseEntity.ok(file);
	}

}
