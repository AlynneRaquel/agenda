package com.matera.agenda.api.exceptionhandler;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiProblemaMessagem {
	
	private Integer Status;
	private LocalDate dataHora;
	private String titulo;
	private List<Campo> campos;

	@AllArgsConstructor
	@Getter
	public static class Campo {

		private String nome;
		private String mensagem;
	}
}
