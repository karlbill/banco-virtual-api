package br.com.nossobanco.api.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nossobanco.api.storage.DiretorioFotos;
import br.com.nossobanco.domain.exception.NegocioException;
import br.com.nossobanco.domain.model.Cliente;
import br.com.nossobanco.domain.repository.ClienteRepository;
import br.com.nossobanco.domain.service.CadastroClienteService;


/**Nome: ClienteController.java
 * Propósito: Classe responsável pelo controle do cadastro de clientes  
 * @author Carlos Guilherme M. B. Abdalla
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private DiretorioFotos discolocal;
	
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll(); 
	}
	
	
	
	/**
	 * Propósito: Busca por um cliente específico
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: retorna a resposta HTTP e o respectivo objeto JSON  
	 */
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build(); //cliente.orElse(null);
	}
	
	/**
	 * Propósito: Inclui um novo cliente na base
	 * @param: cliente(Cliente, objeto JSON com todos os atributos obrigatórios)
	 * @return: retorna a resposta HTTP e o respectivo objeto JSON incluído na base  
	 */
	@PostMapping("/incluir")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		
		return cadastroCliente.salvar(cliente);
	}
	
	/**
	 * Propósito: Atualiza os dados de um cliente na base
	 * @param: clienteId(Long, código do cliente é obrigatório)
	 * @param: cliente(Cliente, os atributos do cliente são obrigatórios)
	 * @return: retorna a resposta HTTP e o respectivo objeto JSON atualizado na base  
	 */
	@PutMapping("/atualizar/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	/**
	 * Propósito: Remove um cliente da base
	 * @param: clienteId(Long, código do cliente é obrigatório)
	 * @return: retorna a resposta HTTP para "NO CONTENT" ou "NOT FOUND" caso não exista o objeto na base  
	 */
	@DeleteMapping("/remover/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroCliente.excluir(clienteId);
		
		return ResponseEntity.noContent().build(); 
	}
	
	/**
	 * Propósito: Realiza o upload da foto do CPF do cliente
	 * @param: clienteId(Long, código do cliente é obrigatório)
	 * @return: retorna o objeto JSON relativo ao cliente
	 * @throws: caso não encontre o cliente, lança a exceção personalizada NegocioException  
	 */
	@PostMapping("/fotos/{clienteId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente uploadFoto(@RequestParam MultipartFile foto, @PathVariable Long clienteId) {
		Path caminho = discolocal.salvarFoto(foto);
		
		if(!clienteRepository.existsById(clienteId)) {
			throw new NegocioException("Cliente não encontrado.");
		}
		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		return cadastroCliente.salvarImagem(cliente.get(), caminho.toString());
	}
}
