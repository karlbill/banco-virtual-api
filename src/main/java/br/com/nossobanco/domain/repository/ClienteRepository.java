package br.com.nossobanco.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nossobanco.domain.model.Cliente;

/**Nome: ClienteRepository.java
 * Propósito: Repositório contendo os métodos personalizados para consulta de clientes no banco de dados.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	/**
	 * Propósito: Busca por uma lista de clientes com um nome específico.
	 * @param: nome(String, nome obrigatório)
	 * @return: List<Cliente>
	 */
	List<Cliente> findByNome(String nome);
	
	/**
	 * Propósito: Busca por uma lista de clientes baseada na informação de parte de um nome.
	 * @param: nome(String, nome obrigatório)
	 * @return: List<Cliente>
	 */
	List<Cliente> findByNomeContaining(String nome);
	
	/**
	 * Propósito: Busca por um cliente específico, baseado no email informado.
	 * @param: email(String, email obrigatório)
	 * @return: Cliente
	 */
	Cliente findByEmail(String email);
	
	/**
	 * Propósito: Busca por um cliente específico, baseado no CPF informado.
	 * @param: cpf(String, cpf obrigatório)
	 * @return: Cliente
	 */
	Cliente findByCpf(String cpf);
	
}
