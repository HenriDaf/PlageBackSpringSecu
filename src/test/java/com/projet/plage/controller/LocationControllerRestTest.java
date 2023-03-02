package com.projet.plage.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.plage.dao.ConcessionnaireDao;
import com.projet.plage.dto.FileDto;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.dto.LocationDto;
import com.projet.plage.dto.ParasolDto;
import com.projet.plage.dto.StatutDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.entity.File;
import com.projet.plage.entity.LienDeParente;
import com.projet.plage.entity.Locataire;
import com.projet.plage.entity.Location;
import com.projet.plage.entity.Parasol;
import com.projet.plage.entity.Pays;
import com.projet.plage.entity.Statut;
import com.projet.plage.service.IConcessionnaireService;
import com.projet.plage.service.IFileService;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.ILocataireService;
import com.projet.plage.service.IParasolService;
import com.projet.plage.service.IPaysService;
import com.projet.plage.service.IStatutService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocationControllerRestTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ILienDeParenteService iLienDeParenteService;
	
	@MockBean
	private static  IPaysService iPaysService;
	
	@MockBean
	private static  IStatutService statutService;
	
	@MockBean
	private static  IParasolService parasolService;
	
	@MockBean
	private static  IFileService fileService;		
		
	/*
	private final List<ParasolDto> listeParasol = new ArrayList<>();
	
	//private Concessionnaire concessionnaire = new Concessionnaire("Greg", "John", "greg@orsys.fr", "abcdefghi", "0215123050");
	
	private static final String nom="Marcus";
	private static final String prenom="Marc";
	private static final String email="string@orsys.fr";
	private static final String password="123456789";	
    private LienDeParente lienDeParente = new LienDeParente("aucun",1);
	private   Pays pays = new Pays("FRA","France");
		
	
	@Test
	@Order(1)
	void testAjoutLocation() throws Exception {
		
		//les données
		StatutDto statut = new StatutDto("confirmée");		
		FileDto file = new FileDto();
		file.setId(5L);		
		file.setNumero((byte)4);
		file.setPrixJournalier(25);
		ParasolDto parasol = new ParasolDto(4l, (byte)7, file);
		listeParasol.add(parasol);
				
		LocataireDto locataire = new LocataireDto();
		locataire.setPays(pays);
		locataire.setLienDeParente(lienDeParente);
		locataire.setEmail(email);
		locataire.setPassword(password);
		locataire.setPrenom(prenom);
		locataire.setNom(nom);
		locataire.setId(2L);		

		 LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(1);
		 LocalDateTime newDate = currentDateTime.plusDays(1); 
		 
		 //création objet location
		 LocationDto location = new LocationDto(currentDateTime, newDate, 30, "On fait un test", 2L, listeParasol, statut);
		 String locationJSON = objectMapper.writeValueAsString(location);
		 
		
		mockMvc.perform(
				 MockMvcRequestBuilders.post("/api/locations/create") .content(locationJSON)
				 .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))							 		
				 .andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print()); // on affiche dans la console la requête		
		
	}*/

}
