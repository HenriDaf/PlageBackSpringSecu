package com.projet.plage.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *   
 * @throws Exception
 * 
 * 
 * Classe de test pour le controlleur rest concessionnaire.
  Utilisation d'un mock, pour mimer le fonctionnement de ce controlleur rest.
  On n'y teste:
  -La récupération des concessionnaires, la création et l'authentification.
  
  Ces test seront effectués sur une base H2, dédiée spécifiquement à la réalisation des tests.
 */

  


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConcessionnaireControllerRestTest {

	@Autowired
	private MockMvc mockMvc;

	private static final String nom = "Doe";
	private static final String prenom = "John";
	private static final String email = "jd@orsys.fr";
	private static final String password = "123456789";
	private static final String numeroDeTelephone = "123456789";

	private static final String emailErrone = "blabla@orsys.fr";
	private static final String passwordErrone = "azertyui";

	private static JSONObject jo = new JSONObject();
	private static JSONObject jo1 = new JSONObject();

	
	
/*
	
	@Test
	@Order(1)
	void testerRecupererListeConcessionnaire1() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/recupererListeConcessionnaire");

		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
																					
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@Order(2)
	void testerAjouterConcessionnaire() throws Exception {
		jo.put("email", email);
		jo.put("password", password);
		jo.put("nom", nom);
		jo.put("prenom", prenom);
		jo.put("numeroDeTelephone", numeroDeTelephone);

		mockMvc.perform(MockMvcRequestBuilders.post("/creationConcessionnaire").contentType(MediaType.APPLICATION_JSON)
				.content(jo.toString())).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(nom))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(prenom))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email))
				//.andExpect(MockMvcResultMatchers.jsonPath("password").value(password))
				.andExpect(MockMvcResultMatchers.jsonPath("$.numeroDeTelephone").value(numeroDeTelephone))
				.andDo(MockMvcResultHandlers.print())
				.andReturn()
				;

	}

	@Test
	@Order(3)
	void testerRecupererListeConcessionnaire2() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/recupererListeConcessionnaire");

		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
																					
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Order(4)
	void testerAuthentificationConcessionnaire() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authentifierConcessionnaire");

		mockMvc.perform(requestBuilder

				.contentType(MediaType.APPLICATION_JSON).content((jo.toString()))).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").isEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(nom))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(prenom))
				.andExpect(MockMvcResultMatchers.jsonPath("$.numeroDeTelephone").value(numeroDeTelephone))
				.andDo(MockMvcResultHandlers.print());

	}

	
	
	@Test
	@Order(4)
	void testerAuthentificationConcessionnaireFalse() throws Exception {

		jo1.put("email", emailErrone);
		jo1.put("password", passwordErrone);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authentifierConcessionnaire");

		mockMvc.perform(requestBuilder

				.contentType(MediaType.APPLICATION_JSON).content((jo1.toString())))
				.andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());

	}
	

	/*
	 * public static String asJsonString(final Object obj) { try { return new
	 * ObjectMapper().writeValueAsString(obj); } catch (Exception e) { throw new
	 * RuntimeException(); }
	 * 
	 * }
	 */

}
