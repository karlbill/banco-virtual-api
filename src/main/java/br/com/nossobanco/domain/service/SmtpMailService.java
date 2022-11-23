package br.com.nossobanco.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**Nome: SmtpMailService.java
 * Propósito: Classe responsável pelo envio real do email.
 * @author Carlos Guilherme M. B. Abdalla   
 */
public class SmtpMailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);

	@Autowired
	private MailSender mailSender;

	/**
	 * Propósito: Envia o email com a proposta.
	 * @param: message(SimpleMailMessage, mensagem obrigatória.) 
	 */
	@Override
	public void enviaEmail(SimpleMailMessage message) {
		LOG.info("Enviando email...");
		mailSender.send(message);
		LOG.info("Email enviado");
	}

}
