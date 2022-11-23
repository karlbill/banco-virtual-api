package br.com.nossobanco.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.nossobanco.domain.exception.EntidadeNaoEncontradaException;
import br.com.nossobanco.domain.exception.NegocioException;

/**Nome: ApiExceptionHandler.java
 * Propósito: Classe responsável pelo gerenciamento das mensagens 
 * 			  de exceção lançadas no sistema.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;

	
	/**
	 * Propósito: Herdado da Classe pai ResponseEntityExceptionHandler, 
	 * 			  responsável por montar o objeto JSON com os erros de 
	 * 			  validação de cada atributo das entidades a serem cadastradas.
	 * @param: ex(MethodArgumentNotValidException, obrigatório)
	 * @param: headers(HttpHeaders, obrigatório)
	 * @param: status(HttpStatus, obrigatório)
	 * @param: request(WebRequest, obrigatório)
	 * @return: retorna o método handleExceptionInternal da classe pai.  
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		var campos = new ArrayList<Problema.Campo>();
		String alerta = "Um ou mais campos estão inválidos.";
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		var problema = new Problema(status.value(), alerta, LocalDateTime.now(), campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	/**
	 * Propósito: Responsável pelo lançamento da mensagem de exceção para os casos "BAD REQUEST"  
	 * @param: ex(NegocioException, obrigatório)
	 * @param: request(WebRequest, obrigatório)
	 * @return: retorna o método handleExceptionInternal da classe pai.  
	 */
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		var problema = new Problema(status.value(), ex.getMessage(),LocalDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	/**
	 * Propósito: Responsável pelo lançamento da mensagem de exceção para os casos "UNPROCESSABLE_ENTITY"  
	 * @param: ex(NegocioException, obrigatório)
	 * @param: request(WebRequest, obrigatório)
	 * @return: retorna o método handleExceptionInternal da classe pai.  
	 */
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request){
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		var problema = new Problema(status.value(), ex.getMessage(), LocalDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
}
