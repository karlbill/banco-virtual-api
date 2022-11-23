package br.com.nossobanco.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.nossobanco.domain.model.Proposta;

/**Nome: AbstractEmailService.java
 * Propósito: Classe abstrata que contém os métodos para envio de email com a proposta a ser aceita pelo cliente.
 * @author Carlos Guilherme M. B. Abdalla   
 */
public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;

	
	/**
	 * Propósito: Recebe a proposta do cliente e a envia por email.
	 * @param: proposta(Proposta, proposta obrigatória) 
	 */
	@Override
	public void enviaPropostaParaConfirmacao(Proposta proposta) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromProposta(proposta);
		enviaEmail(sm);
	}

	/**
	 * Propósito: Prepara a proposta do cliente para ser enviada por email.
	 * @param: proposta(Proposta, proposta obrigatória)
	 * @return: SimpleMailMessage  
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromProposta(Proposta proposta) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(proposta.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Proposta "+ proposta.getId() +"gerada!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(proposta.toString());

		return sm;
	}
}