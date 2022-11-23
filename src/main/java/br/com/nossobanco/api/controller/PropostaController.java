package br.com.nossobanco.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nossobanco.domain.exception.EntidadeNaoEncontradaException;
import br.com.nossobanco.domain.exception.NegocioException;
import br.com.nossobanco.domain.model.Proposta;
import br.com.nossobanco.domain.model.StatusProposta;
import br.com.nossobanco.domain.repository.PropostaRepository;
import br.com.nossobanco.domain.service.CadastroClienteService;
import br.com.nossobanco.domain.service.EmailService;

/**
 * Nome: PropostaController.java
 * Propósito: Classe responsável pelo controle da geração de novas propostas para abertura de conta.
 * @author Carlos Guilherme M. B. Abdalla 
 */
@RestController
@RequestMapping("/cliente/{clienteId}/proposta")
public class PropostaController {
	
	@Autowired
	private CadastroClienteService cadastroService;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	/**
	 * Propósito: Cria uma nova proposta de abertura de conta para o cliente já cadastrado.
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: retorna a resposta HTTP "CREATED" e o respectivo objeto JSON salvo na base. 
	 * @throws: caso encontre uma proposta já cadastrada para o cliente, 
	 * 			lança a exceção personalizada NegocioException.   
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Proposta gerar(@PathVariable Long clienteId) {
		Boolean existeProposta = propostaRepository.findByClienteId(clienteId).isPresent();
		
		if(existeProposta) {
			throw new NegocioException("Já existe uma proposta cadastrada para este cliente.");
		}
		
		Proposta proposta = cadastroService.gerarProposta(clienteId);
		
		return proposta;
	}
	
	/**
	 * Propósito: Responsável pelo aceite da proposta enviada ao cliente.
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: retorna a resposta HTTP "CREATED" e o respectivo objeto JSON salvo na base. 
	 * @throws: caso não haja uma proposta ou a mesma não esteja com status "ABERTA",
	 * 			lança a exceção personalizada NegocioException.   
	 */
	@PostMapping("/aceite")
	@ResponseStatus(HttpStatus.CREATED)
	public Proposta aceite(@PathVariable Long clienteId) {
		Proposta propostaAberta = propostaRepository.findByClienteId(clienteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não possui proposta."));
		
		if(propostaAberta.equals(null) || !propostaAberta.getStatus().equals(StatusProposta.ABERTA)) {
			throw new NegocioException("Proposta do cliente não está aberta.");
		}
		
		Proposta proposta = cadastroService.aceitarProposta(propostaAberta);
		emailService.enviaPropostaParaConfirmacao(proposta);
		
		return proposta;
	}

}
