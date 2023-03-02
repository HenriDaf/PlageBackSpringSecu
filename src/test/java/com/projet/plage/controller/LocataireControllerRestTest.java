package com.projet.plage.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.plage.dto.LocataireDto;
import com.projet.plage.entity.Concessionnaire;
import com.projet.plage.entity.LienDeParente;
import com.projet.plage.entity.Pays;
import com.projet.plage.service.ILienDeParenteService;
import com.projet.plage.service.IPaysService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocataireControllerRestTest {

/*	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ILienDeParenteService iLienDeParenteService;
	
	@MockBean
	private static  IPaysService iPaysService;
	
	private static final String nom="Doe";
	private static final String prenom="John";
	private static final String email="jd@orsys.fr";
	private static final String password="123456789";
//	private static final String lienDeParente="aucun";
	//private static final String pays="France";
	
    private LienDeParente lienDeParente = new LienDeParente("aucun",1);
	private   Pays pays = new Pays("FRA","France");
	
	
	private  LocataireDto locataireDto= new LocataireDto(nom, prenom, email, password, lienDeParente,pays);
//	private static final Concessionnaire CONCESSIONNAIRE = new Concessionnaire(nom, prenom, email, numero, lienDeParente,pays);
//	LocataireDto locataireDto=new LocataireDto(nom, prenom, email, password,iLienDeParenteService.recupererLienDeParenteParNom(lienDeParente), iPaysService.recupererPaysParNom(pays));
	
//	@Test
//	@Order(1)
	 void testerAjouterLocataire() throws Exception {
	
	mockMvc.perform(
	 MockMvcRequestBuilders.post("/creationLocataire")
	 .contentType(MediaType.APPLICATION_JSON)
	 .content(asJsonString(locataireDto)))
	 .andExpect(status().isCreated())
	.andDo(MockMvcResultHandlers.print());
	
	
	
		
	}
	
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch (Exception e) {
		throw new RuntimeException();
		}
		
	}*/


}
