package br.com.nossobanco.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nossobanco.domain.model.Endereco;

/**Nome: EnderecoRepository.java
 * Propósito: Repositório contendo os métodos personalizados para consulta/exclusão de endereços no banco de dados.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	/**
	 * Propósito: Busca por um endereço específico.
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: Optional<Endereco>
	 */
	Optional<Endereco> findByClienteId(Long clienteId);
	
	/**
	 * Propósito: Exclui um endereço específico.
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: Endereco
	 */
	Endereco deleteByClienteId(Long clienteId);
}
