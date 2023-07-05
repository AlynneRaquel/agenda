package com.matera.agenda.controller;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.matera.agenda.api.controller.ContatoController;
import com.matera.agenda.domain.model.Contato;
import com.matera.agenda.domain.repository.ContatoRepository;

import io.restassured.http.ContentType;

@WebMvcTest
public class ContatoControllerTest {
	
	@Autowired
	private ContatoController contatoController;
	
	@MockBean
	private ContatoRepository contatoRepository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.contatoController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarContato() {
		
		when(this.contatoRepository.findById(1L))
			.thenReturn(Optional.of(new Contato(1L,"Alynne Raquel", "alynne@hotmail.com", "87", "Petrolina-Pe")));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/contatos/{contatoId}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEcontrado_QuandoBuscarContato() {
		
		when(this.contatoRepository.findById(10L))
				.thenReturn(Optional.ofNullable(null));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/contatos/{contatoId}", 10L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
