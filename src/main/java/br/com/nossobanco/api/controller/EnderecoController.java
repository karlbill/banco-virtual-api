package br.com.nossobanco.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nossobanco.domain.exception.NegocioException;
import br.com.nossobanco.domain.model.Endereco;
import br.com.nossobanco.domain.repository.ClienteRepository;
import br.com.nossobanco.domain.repository.EnderecoRepository;
import br.com.nossobanco.domain.service.CadastroEnderecoService;


/**Nome: EnderecoController.java
 * Propósito: Classe responsável pelo controle do cadastro de endereço dos clientes
 * @author Carlos Guilherme M. B. Abdalla   
 */
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private CadastroEnderecoService cadastroEndereco;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	/**
	 * Propósito: Adiciona um novo endereço para o cliente informado
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: retorna a resposta HTTP "CREATED" e o respectivo objeto JSON salvo na base 
	 * @throws: caso não encontre o cliente, lança a exceção personalizada NegocioException   
	 */
	@PostMapping("/incluir/{clienteId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco adicionar(@Valid @RequestBody Endereco endereco, @PathVariable Long clienteId) {
		var cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isEmpty()) {
			throw new NegocioException("Cliente não encontrado");
		}
		
		endereco.setCliente(cliente.get());
		
		return cadastroEndereco.salvar(endereco);
	}
	
	/**
	 * Propósito: Lista todos os endereço cadastrados na base
	 * @return: retorna a resposta HTTP e os objetos JSON cadastrados. 
	 * 			Caso não encontre, retorna o HTTP "NOT FOUND"  
	 */
	@GetMapping
	public List<Endereco> listar(){
		return enderecoRepository.findAll();
	}
	
	/**
	 * Propósito: Lista o endereço de um cliente específico
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: retorna a resposta HTTP e o objeto JSON específico 
	 * 			Caso não encontre, retorna o HTTP "NOT FOUND"    
	 */
	@GetMapping("{clienteId}") 
	public ResponseEntity<Endereco> buscar(@PathVariable Long clienteId){
		var endereco = enderecoRepository.findByClienteId(clienteId); 
		
		if(!endereco.isEmpty()) {
			return ResponseEntity.ok(endereco.get());
		}
		
		return ResponseEntity.notFound().build();  
	}
	
}
