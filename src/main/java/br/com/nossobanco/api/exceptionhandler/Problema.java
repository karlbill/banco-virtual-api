package br.com.nossobanco.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**Nome: Problema.java
 * Propósito: Classe será montada com os devidos atributos que gerarem erro no cadastro de entidades. 
 * @author Carlos Guilherme M. B. Abdalla   
 */
@JsonInclude(Include.NON_NULL)
public class Problema {

	private int status;
	private String titulo;
	private LocalDateTime dataHora;
	private List<Campo> campos;
	
	/**Nome: Campo.java
	 * Propósito: Classe receberá o nome do atributo que gerou a exceção e a mensagem de exceção 
	 * @author Carlos Guilherme M. B. Abdalla   
	 */
	public static class Campo{
		
		private String nome;
		private String mensagem;
		
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		
	}
	
	public Problema() {
		super();
	}
	
	public Problema(int status, String titulo, LocalDateTime dataHora) {
		super();
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
	}

	public Problema(int status, String titulo, LocalDateTime dataHora, List<Campo> campos) {
		super();
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.campos = campos;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
}
