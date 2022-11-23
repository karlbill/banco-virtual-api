package br.com.nossobanco.domain.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.nossobanco.domain.model.Proposta;

/**Nome: EmailService.java
 * Propósito: Interface responsável pelos métodos de envio e confirmação da proposta.
 * @author Carlos Guilherme M. B. Abdalla   
 */
public interface EmailService {
	
	/**
	 * Propósito: Envia proposta de abertura de conta para confirmação
	 * @param: proposta(Proposta, proposta obrigatória) 
	 */
	void enviaPropostaParaConfirmacao(Proposta proposta);
	
	/**
	 * Propósito: Envia email para o cliente
	 * @param: message(SimpleMailMessage, mensagem de email obrigatória.) 
	 */
	void enviaEmail(SimpleMailMessage message);
}
