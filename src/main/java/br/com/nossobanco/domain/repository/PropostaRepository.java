package br.com.nossobanco.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nossobanco.domain.model.Proposta;

/**Nome: PropostaRepository.java
 * Propósito: Repositório contendo os métodos personalizados para consulta de propostas no banco de dados.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	/**
	 * Propósito: Busca por um proposta específica.
	 * @param: clienteId(Long, código do cliente obrigatório)
	 * @return: Optional<Proposta>
	 */
	Optional<Proposta> findByClienteId(Long clienteId);
	
}
