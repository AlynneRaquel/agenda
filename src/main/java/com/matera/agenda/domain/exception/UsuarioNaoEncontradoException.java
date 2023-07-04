package com.matera.agenda.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(Long id) {
		super(String.format("Usuário com id %d não encontrado. ", id));
	}

}
