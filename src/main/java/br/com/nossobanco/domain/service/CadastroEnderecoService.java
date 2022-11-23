package br.com.nossobanco.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nossobanco.domain.model.Endereco;
import br.com.nossobanco.domain.repository.EnderecoRepository;

/**Nome: CadastroEnderecoService.java
 * Propósito: Classe da camada de serviço, responsável pelo cadastro de endereço dos clientes.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Service
public class CadastroEnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	/**
	 * Propósito: Recebe os dados do endereço cadastrado pelo usuário e salva no banco de dados.
	 * @param: endereco(Endereco, endereço obrigatório) 
	 * @return: Endereco
	 */
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	/**
	 * Propósito: Recebe o código de um cliente específico e exclui seu endereço da base.
	 * @param: clienteId(Long, código do cliente é obrigatório) 
	 */
	public void excluir(Long clienteId) {
		enderecoRepository.deleteByClienteId(clienteId);
	}
}
