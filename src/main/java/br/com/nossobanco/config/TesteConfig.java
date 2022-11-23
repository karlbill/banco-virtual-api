package br.com.nossobanco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.nossobanco.domain.service.EmailService;
import br.com.nossobanco.domain.service.MockMailService;

/**Nome: TesteConfig.java
 * Propósito: Classe responsável pelo perfil de Teste da aplicação
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Configuration
@Profile("test")
public class TesteConfig {

	/**
	 * Propósito: Simula o envio de email com a proposta a ser aceita pelo cliente.
	 * @return: retorna o objeto MockMailService 
	 */
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
}
