package br.com.virtualPet.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.virtualPet.service.exception.NomeJaCadastradoException;



@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	
	@ExceptionHandler(NomeJaCadastradoException.class)
	public ResponseEntity<String> handlerNomeJaCadastradoException(NomeJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}

}