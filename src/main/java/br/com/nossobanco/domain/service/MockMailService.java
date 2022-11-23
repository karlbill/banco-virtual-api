package br.com.nossobanco.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**Nome: MockMailService.java
 * Propósito: Classe responsável pela simulação do envio de email.
 * @author Carlos Guilherme M. B. Abdalla   
 */
public class MockMailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);

	/**
	 * Propósito: Simula o envio de email para o cliente.
	 * @param: message(SimpleMailMessage, mensagem obrigatória.) 
	 */
	@Override
	public void enviaEmail(SimpleMailMessage message) {
		LOG.info("Enviando email de simulação...");
		LOG.info(message.toString());
		LOG.info("Email enviado");
	}

	

}
