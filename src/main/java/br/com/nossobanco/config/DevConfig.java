package br.com.nossobanco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.nossobanco.domain.service.EmailService;
import br.com.nossobanco.domain.service.SmtpMailService;


/**Nome: DevConfig.java
 * Propósito: Classe responsável pelo perfil de Desenvolvimento da aplicação
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Configuration
@Profile("dev")
public class DevConfig {

	/**
	 * Propósito: Envia um email real com a proposta a ser aceita pelo cliente.
	 * @return: retorna o objeto SmtpMailService 
	 */
	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}
}
