package com.matera.agenda.api.exceptionhandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.matera.agenda.domain.exception.UsuarioNaoEncontradoException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UsuarioNaoEncontradoException exception,
			WebRequest request) {
	
		ApiErroMessagem apiErrorMessage = new ApiErroMessagem(HttpStatus.NOT_FOUND, exception.getMessage());

		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ApiProblemaMessagem.Campo> campos = new ArrayList<>();

		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());		

			campos.add(new ApiProblemaMessagem.Campo(nome, mensagem));

		}

		ApiProblemaMessagem problema = new ApiProblemaMessagem();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDate.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);

	}

}
