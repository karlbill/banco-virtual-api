package br.com.nossobanco.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nossobanco.domain.exception.NegocioException;
import br.com.nossobanco.domain.model.Cliente;
import br.com.nossobanco.domain.model.Proposta;
import br.com.nossobanco.domain.model.StatusProposta;
import br.com.nossobanco.domain.repository.ClienteRepository;
import br.com.nossobanco.domain.repository.PropostaRepository;

/**Nome: CadastroClienteService.java
 * Propósito: Classe da camada de serviço responsável pelo cadastro de clientes. 
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
		
	@Autowired
	private PropostaRepository propostaRepository;
	
	
	/**
	 * Propósito: Recebe o objeto cliente e acessa o repositório para salvá-lo no banco de dados.
	 * @param: cliente(Cliente, cliente obrigatório)
	 * @return: Cliente
	 * @throws: Caso já exista um cliente com o CPF informado, lança a exceção NegocioException  
	 */
	public Cliente salvar(Cliente cliente) {
		Cliente cpfExistente = clienteRepository.findByCpf(cliente.getCpf());
		
		if(cpfExistente != null && !cpfExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este cpf.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	/**
	 * Propósito: Exclui um cliente da base.
	 * @param: clienteId(Long, código do cliente é obrigatório) 
	 */
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
	/**
	 * Propósito: Salva a foto do CPF do cliente no caminho especificado.
	 * @param: cliente(Cliente, cliente obrigatório)
	 * @param: caminho(String, caminho obrigatório)
	 * @return: Cliente
	 */
	public Cliente salvarImagem(Cliente cliente, String caminho) {
		cliente.setFoto(caminho);
		
		return clienteRepository.save(cliente);
	}
	
	/**
	 * Propósito: Abre uma proposta para o cliente.
	 * @param: clienteId(Long, código do cliente é obrigatório)
	 * @return: Proposta
	 */
	public Proposta gerarProposta(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		
		Proposta proposta = new Proposta(); 
		proposta.setDataAbertura(OffsetDateTime.now());
		proposta.setCliente(cliente);
		proposta.setStatus(StatusProposta.ABERTA);
		
		return propostaRepository.save(proposta);
	}
	
	/**
	 * Propósito: Realiza o aceite da proposta pelo cliente, alterando o status para "CONFIRMADA".
	 * @param: proposta(Proposta, código do cliente é obrigatório)
	 * @return: Proposta
	 */
	public Proposta aceitarProposta(Proposta proposta) {
		proposta.setStatus(StatusProposta.CONFIRMADA);
		
		return propostaRepository.save(proposta);
	}
	
	
}
