package br.com.nossobanco.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**Nome: Proposta.java
 * Propósito: Classe modelo da entidade Proposta
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Cliente cliente;
	
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private StatusProposta status;			
	
	@Column(name = "dt_abertura")
	private OffsetDateTime dataAbertura;
		
		
	public Proposta() {
	
	}

	public Proposta(Cliente cliente, Endereco endereco, StatusProposta status, OffsetDateTime dataAbertura) {
		super();
		this.cliente = cliente;
		this.status = status;
		this.dataAbertura = dataAbertura;
	}

	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public StatusProposta getStatus() {
		return status;
	}
	public void setStatus(StatusProposta status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposta other = (Proposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nProposta: ");
		builder.append(getId());
		builder.append("\nData de abertura: ");
		builder.append(getDataAbertura());
		builder.append("\nCliente: ");
		builder.append(getCliente());
		builder.append("\n\nStatus da proposta: ");
		builder.append(getStatus());
		
		return builder.toString();
	}


}
